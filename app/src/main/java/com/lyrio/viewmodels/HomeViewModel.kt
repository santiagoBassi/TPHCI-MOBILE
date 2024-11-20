package com.lyrio.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lyrio.api.network.LyrioApi
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data object Error : HomeUiState
    data class Success(val data: List<String>) : HomeUiState
}

class HomeViewModel: ViewModel() {
    var homeUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        //ACOMODAR
        getAnswer()
    }
    // ACOMODAR
    fun getAnswer() {
        // API
        viewModelScope.launch {
            homeUiState = HomeUiState.Loading
            homeUiState = try {
                val result = LyrioApi.retrofitService.getUser()
                HomeUiState.Success(listOf(result.firstName))
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }
}