package com.lyrio.ui.data.viewmodels

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
import com.lyrio.data.model.Error
import com.lyrio.data.repository.PaymentRepository
import com.lyrio.data.repository.UserRepository
import com.lyrio.data.repository.WalletRepository
import com.lyrio.ui.data.states.UiState
import com.lyrio.ui.data.states.UserUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel() : ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    fun setSelectedItem(index: Int) {
        _uiState.value = _uiState.value.copy(selectedItem = index)
    }


    companion object {
        const val TAG = "UI Layer"

        fun provideFactory(
            application: LyrioApp
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return com.lyrio.ui.data.viewmodels.ViewModel() as T
            }
        }
    }
}
