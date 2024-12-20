package com.lyrio.ui.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.SessionManager
import com.lyrio.data.DataSourceException
import com.lyrio.data.model.CardType
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
import com.lyrio.data.model.NewCard
import com.lyrio.data.repository.WalletRepository
import com.lyrio.ui.data.states.UserUiState
import com.lyrio.ui.data.states.WalletUiState

class WalletViewModel(
    sessionManager: SessionManager,
    private val walletRepository: WalletRepository,
    userViewModel: UserViewModel
) : ViewModel() {

    private val _uiStateWallet = MutableStateFlow(WalletUiState())
    val uiStateWallet: StateFlow<WalletUiState> = _uiStateWallet.asStateFlow()

    fun getBalance() = runOnViewModelScope(
        {
            walletRepository.getBalance()
        },
        { state, balance -> state.copy(balance = balance) }
    )

    fun getInvested() = runOnViewModelScope(
        {
            walletRepository.getInvestment()
        },
        { state, _ -> state.copy() }
    )

    fun getCards() = runOnViewModelScope(
        {
            walletRepository.getCards()
        },
        { state, response -> state.copy(cards = response) }
    )

    fun getWalletData() = runOnViewModelScope(
        {
            walletRepository.getDetails()
        },
        { state, response -> state.copy(
            alias = response.alias,
            cbu = response.cbu,
            balance = response.balance,
            investedMoney = response.invested
        ) }
    )

    fun updateAlias(alias: String, onSuccessfulUpdate: () -> Unit) = runOnViewModelScope(
        {
            walletRepository.updateAlias(alias)
        },
        { state, response -> state.copy(alias = response.alias)},
        onSuccessfulUpdate
    )

    fun addCreditCard(cardNumber: String, holderName: String, expiryDate: String, cvv: String, onSuccessfulAdd: () -> Unit) = runOnViewModelScope(
        {
            walletRepository.addCard(NewCard(
                number = cardNumber,
                expirationDate = expiryDate,
                fullName = holderName,
                cvv = cvv,
                type = CardType.CREDIT
            ))
        },
        { state, newCard -> state.copy(cards = state.cards + newCard)},
        onSuccessfulAdd
    )

    fun deleteCard(cardId: Int) = runOnViewModelScope(
        {
            walletRepository.deleteCard(cardId)
        },
        { state, _ -> state.copy(cards = state.cards.filter { it.id != cardId }) }
    )

    fun addInvestment(amount: Double) = runOnViewModelScope(
        {
            walletRepository.invest(amount)
        },
        { state, response -> state.copy(
            balance = response.balance,
            investedMoney = response.invested
        ) }
    )

    fun withdrawInvestment(amount: Double) = runOnViewModelScope(
        {
            walletRepository.divest(amount)
        },
        { state, response -> state.copy(
            balance = response.balance,
            investedMoney = response.invested
        ) }
    )

    fun clearError() {
        _uiStateWallet.update { currentState -> currentState.copy(error = null) }
    }

    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (WalletUiState, T) -> WalletUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiStateWallet.update { currentState -> currentState.copy(error = handleError(e)) } }
            .collect { response -> _uiStateWallet.update { currentState -> updateState(currentState, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (WalletUiState, R) -> WalletUiState,
        callback: () -> Unit = { }
    ): Job = viewModelScope.launch {
        _uiStateWallet.update { currentState -> currentState.copy(isFetching = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiStateWallet.update { currentState -> updateState(currentState, response).copy(isFetching = false) }
            callback()
        }.onFailure { e ->
            _uiStateWallet.update { currentState -> currentState.copy(isFetching = false, error = handleError(e)) }
            Log.e(UserViewModel.Companion.TAG, "Coroutine execution failed", e)
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
                return WalletViewModel(
                    application.sessionManager,
                    application.walletRepository,
                    userViewModel) as T
            }
        }
    }
}