package com.lyrio.ui

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.SessionManager
import com.lyrio.data.DataSourceException
import com.lyrio.data.model.Card
import com.lyrio.data.model.Error
import com.lyrio.data.repository.PaymentRepository
import com.lyrio.data.repository.UserRepository
import com.lyrio.data.repository.WalletRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
    private val walletRepository: WalletRepository,
    private val paymentRepository: PaymentRepository
) : ViewModel() {

    var uiState by mutableStateOf(UiState(isAuthenticated = sessionManager.loadAuthToken() != null))
        private set


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // PAYMENT FUNCTIONS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // TODO: IMPLEMENT PAYMENT FUNCTIONS



    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // USER FUNCTIONS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    fun register(firstName: String, lastName: String, birthDate: String, email: String, password: String) = runOnViewModelScope(
        { userRepository.register(firstName, lastName, birthDate, email, password) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun getCurrentUser() = runOnViewModelScope(
        { userRepository.getCurrentUser(uiState.currentUser == null) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun login(username: String, password: String) = runOnViewModelScope(
        { userRepository.login(username, password) },
        { state, _ -> state.copy(isAuthenticated = true) }
    )

    fun verifyEmail(code: String) = runOnViewModelScope(
        { userRepository.verifyEmail(code) },
        { state, response -> state.copy(currentUser = response) }
    )

    fun recoverPassword(email: String) = runOnViewModelScope(
        { userRepository.recoverPassword(email) },
        { state, _ -> state }
    )

    fun resetPassword(code: String, password: String) = runOnViewModelScope(
        { userRepository.resetPassword(code, password) },
        { state, _ -> state }
    )

    fun logout() = runOnViewModelScope(
        { userRepository.logout() },
        { state, _ ->
            state.copy(
                isAuthenticated = false,
                currentUser = null,
                currentCard = null,
                cards = null,
                payments = null,
                currentPayment = null
            )
        }
    )


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // WALLET FUNCTIONS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    // TODO: FINISH IMPLEMENTING WALLET FUNCTIONS
//
//    fun getCards() = runOnViewModelScope(
//        { walletRepository.getCards(true) },
//        { state, response -> state.copy(cards = response) }
//    )
//
//    fun addCard(card: Card) = runOnViewModelScope(
//        {
//            walletRepository.addCard(card)
//        },
//        { state, response ->
//            state.copy(
//                currentCard = response,
//                cards = null
//            )
//        }
//    )
//
//    fun deleteCard(cardId: Int) = runOnViewModelScope(
//        { walletRepository.deleteCard(cardId) },
//        { state, _ ->
//            state.copy(
//                currentCard = null,
//                cards = null
//            )
//        }
//    )


    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // AUXILIARY FUNCTIONS/CONSTANTS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (UiState, R) -> UiState
    ): Job = viewModelScope.launch {
        uiState = uiState.copy(isFetching = true, error = null)
        runCatching {
            block()
        }.onSuccess { response ->
            uiState = updateState(uiState, response).copy(isFetching = false)
        }.onFailure { e ->
            uiState = uiState.copy(isFetching = false, error = handleError(e))
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return if (e is DataSourceException) {
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        }
    }

    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: LyrioApp
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return ViewModel(
                    application.sessionManager,
                    application.userRepository,
                    application.walletRepository,
                    application.paymentRepository) as T
            }
        }
    }
}
