package com.lyrio.ui.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.SessionManager
import com.lyrio.data.DataSourceException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.lyrio.data.model.Error
import com.lyrio.data.repository.PaymentRepository
import com.lyrio.ui.data.states.PaymentsUiState

class PaymentsViewModel(
    sessionManager: SessionManager,
    private val paymentRepository: PaymentRepository,
    userViewModel: UserViewModel
) : ViewModel() {

    private val _uiStatePayments = MutableStateFlow(PaymentsUiState())
    val uiStatePayments: StateFlow<PaymentsUiState> = _uiStatePayments.asStateFlow()

    fun getPayments() = runOnViewModelScope(
        {
            paymentRepository.getPayments()
                .sortedByDescending { it.createdAt }
                .take(10)
        },
        { state, response -> state.copy(lastTransfers = response) }
    )

    fun getLastPayments() = runOnViewModelScope(
        {
            paymentRepository.getPayments()
        },
        { state, response -> state.copy(lastTransfers = response) }
    )

    fun setReceiver(email: String) {
         _uiStatePayments.value = _uiStatePayments.value.copy(receiver = email)
    }

    fun setSelectedPaymentMethod(index: Int) {
        _uiStatePayments.value = _uiStatePayments.value.copy(selectedPaymentMethod = index)
    }

    fun transfer(amount: Double, cardId: Int) = runOnViewModelScope(
        {
            if(_uiStatePayments.value.selectedPaymentMethod == 0)
                paymentRepository.makePaymentWithBalance(amount,"-","BALANCE",_uiStatePayments.value.receiver)
            else
                paymentRepository.makePaymentWithCard(amount,"-","CARD",cardId,_uiStatePayments.value.receiver)
        },
        { state, _ -> state.copy() }
    )


    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (PaymentsUiState, T) -> PaymentsUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiStatePayments.update { currentState -> currentState.copy(error = handleError(e)) } }
            .collect { response -> _uiStatePayments.update { currentState -> updateState(currentState, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (PaymentsUiState, R) -> PaymentsUiState
    ): Job = viewModelScope.launch {
        _uiStatePayments.update { currentState -> currentState.copy(isFetching = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiStatePayments.update { currentState -> updateState(currentState, response).copy(isFetching = false) }
        }.onFailure { e ->
            _uiStatePayments.update { currentState -> currentState.copy(isFetching = false, error = handleError(e)) }
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return (if (e is DataSourceException) {
            Error(e.code, e.message.toString())
        } else {
            Error(null, e)
        }) as Error
    }

    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: LyrioApp,
            userViewModel: UserViewModel
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PaymentsViewModel(
                    application.sessionManager,
                    application.paymentRepository,
                    userViewModel) as T
            }
        }
    }
}