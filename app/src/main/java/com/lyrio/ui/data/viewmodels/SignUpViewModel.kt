package com.lyrio.ui.data.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lyrio.LyrioApp
import com.lyrio.data.DataSourceException
import com.lyrio.data.model.Error
import com.lyrio.data.repository.UserRepository
import com.lyrio.ui.data.states.SignUpUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date

class SignUpViewModel(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _signUpUiState = MutableStateFlow(SignUpUiState())
    val signUpUiState: StateFlow<SignUpUiState> = _signUpUiState.asStateFlow()

    fun register(email: String, password: String) = runOnViewModelScope(
        { userRepository.register(firstName = signUpUiState.value.firstName, lastName =  signUpUiState.value.lastName, birthDate =  "2000-04-10", email =  email, password = password) },
        { state, _ -> state }
    )

    fun completeForm1(firstName: String, lastName: String, birthDate: Date) {
//        _signUpUiState.update { currentState ->
//            currentState.copy(firstName = firstName, lastName = lastName, dateOfBirth = birthDate)
//        }
        _signUpUiState.value = SignUpUiState(firstName = firstName, lastName = lastName, dateOfBirth = birthDate)
    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // AUXILIARY FUNCTIONS/CONSTANTS
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private fun <R> runOnViewModelScope(
        block: suspend () -> R,
        updateState: (SignUpUiState, R) -> SignUpUiState
    ): Job = viewModelScope.launch {
        _signUpUiState.update { currentState ->
            currentState.copy(isFetching = true, error = null)
        }
        runCatching {
            block()
        }.onSuccess { response ->
            _signUpUiState.update { currentState ->
                currentState.copy(isFetching = false)
            }
        }.onFailure { e ->
            _signUpUiState.update { currentState ->
                currentState.copy(isFetching = false, error = handleError(e))
            }
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
                return SignUpViewModel(
                    application.userRepository) as T
            }
        }
    }
}
