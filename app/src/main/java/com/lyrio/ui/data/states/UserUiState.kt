package com.lyrio.ui.data.states

import com.lyrio.data.model.Error

data class UserUiState(
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: String = "",
    val email: String = "",
    val password: String = "",
    val code: String = "",
    val isFetching: Boolean = false,
    val error: Error? = null
)