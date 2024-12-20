package com.lyrio.ui.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.SessionManager
import com.lyrio.data.DataSourceException
import com.lyrio.data.repository.UserRepository
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
import com.lyrio.ui.data.states.UserUiState

class UserViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiStateUser = MutableStateFlow(UserUiState())
    val uiStateUser: StateFlow<UserUiState> = _uiStateUser.asStateFlow()


    fun completeFormRegister1(firstName: String, lastName: String, birthDate: String) {
        _uiStateUser.value = _uiStateUser.value.copy(firstName = firstName, lastName = lastName, dateOfBirth = birthDate)
    }

    fun register(firstName: String, lastName: String, dateOfBirth: String, email: String, password: String, onSuccessfulRegister: () -> Unit) = runOnViewModelScope(
        {
            userRepository.register(firstName, lastName, dateOfBirth, email, password)
        },
        { state, _ -> state.copy() },
        onSuccessfulRegister
    )

    fun login(email: String, password: String, onSuccessfulLogin: () -> Unit) = runOnViewModelScope(
        {
            userRepository.login(email, password)
        },
        { state, _ -> state.copy(isAuthenticated = true) },
        onSuccessfulLogin
    )

    fun verifyEmail(code: String, navigateSignIn: () -> Unit) = runOnViewModelScope(
        {
            userRepository.verifyEmail(code)
        },
        { state, _ -> state.copy() },
        navigateSignIn
    )

    fun recoverPass1(email: String, onSuccessfulRecover: () -> Unit) = runOnViewModelScope(
        {
            userRepository.recoverPassword(email)
        },
        {state, _ -> state.copy()},
        onSuccessfulRecover
    )

    fun recoverPass2(password: String, navigateSignIn: () -> Unit) = runOnViewModelScope(
        {
            userRepository.resetPassword(_uiStateUser.value.code, password)
        },
        {state, _ -> state.copy()},
        navigateSignIn
    )

    fun saveCode(code: String, navigateRecoverPass3: () -> Unit) {
        _uiStateUser.value = _uiStateUser.value.copy(code = code)
        navigateRecoverPass3
    }

    fun getCurrentUser() = runOnViewModelScope(
        {
            userRepository.getCurrentUser(false)
        },
        {state, response -> state.copy(
            email = response?.email ?: "",
            firstName = response?.firstName ?: "",
            lastName = response?.lastName ?: "",
            dateOfBirth = response?.birthDate ?: "")}
    )

    fun logout() = runOnViewModelScope(
        {
            userRepository.logout()
        },
        {state, _ -> state.copy(isAuthenticated = false)}
    )





    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (UserUiState, T) -> UserUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiStateUser.update { currentState -> currentState.copy(error = handleError(e)) } }
            .collect { response -> _uiStateUser.update { currentState -> updateState(currentState, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (UserUiState, R) -> UserUiState,
        callback: () -> Unit = { }
    ): Job = viewModelScope.launch {
        _uiStateUser.update { currentState -> currentState.copy(isFetching = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiStateUser.update { currentState -> updateState(currentState, response).copy(isFetching = false) }
            callback()
        }.onFailure { e ->
            _uiStateUser.update { currentState -> currentState.copy(isFetching = false, error = handleError(e)) }
            Log.e(TAG, "Coroutine execution failed", e)
        }
    }

    private fun handleError(e: Throwable): Error {
        return (if (e is DataSourceException) {
            Log.e(TAG, "Este es el error" + e.code.toString(), e)
            Error(e.code, e.message ?: "")
        } else {
            Error(null, e.message ?: "")
        })
    }

    fun clearError() {
        _uiStateUser.update { currentState -> currentState.copy(error = null) }
    }


    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: LyrioApp
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return UserViewModel(
                    application.sessionManager,
                    application.userRepository) as T
            }
        }
    }
}