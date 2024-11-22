package com.lyrio.ui.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.SessionManager
import com.lyrio.data.DataSourceException
import com.lyrio.data.repository.UserRepository
import com.lyrio.ui.data.states.SignUpUiState
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
import java.util.Date

class SignUpViewModel(
    sessionManager: SessionManager,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _uiStateSignUp = MutableStateFlow(SignUpUiState())
    val uiStateSignUp: StateFlow<SignUpUiState> = _uiStateSignUp.asStateFlow()


    fun register(firstName: String, lastName: String, dateOfBirth: String, email: String, password: String) = runOnViewModelScope(
        {
            userRepository.register(firstName, lastName, dateOfBirth, email, password)
        },
        { state, _ -> state.copy() }
    )


    fun completeFormRegister1(firstName: String, lastName: String, birthDate: String) {
        _uiStateSignUp.value = _uiStateSignUp.value.copy(firstName = firstName, lastName = lastName, dateOfBirth = birthDate)
    }



    private fun <T> collectOnViewModelScope(
        flow: Flow<T>,
        updateState: (SignUpUiState, T) -> SignUpUiState
    ) = viewModelScope.launch {
        flow
            .distinctUntilChanged()
            .catch { e -> _uiStateSignUp.update { currentState -> currentState.copy(error = handleError(e)) } }
            .collect { response -> _uiStateSignUp.update { currentState -> updateState(currentState, response) } }
    }

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (SignUpUiState, R) -> SignUpUiState
    ): Job = viewModelScope.launch {
        _uiStateSignUp.update { currentState -> currentState.copy(isFetching = true, error = null) }
        runCatching {
            block()
        }.onSuccess { response ->
            _uiStateSignUp.update { currentState -> updateState(currentState, response).copy(isFetching = false) }
        }.onFailure { e ->
            _uiStateSignUp.update { currentState -> currentState.copy(isFetching = false, error = handleError(e)) }
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
            application: LyrioApp
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return SignUpViewModel(
                    application.sessionManager,
                    application.userRepository) as T
            }
        }
    }
}