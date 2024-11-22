package com.lyrio.ui.data.states

import com.lyrio.data.model.Error
import java.util.Date

data class SignUpUiState(
    val firstName: String = "",
    val lastName: String = "",
    val dateOfBirth: Date = Date(),
    val email: String = "",
    val password: String = "",
    val isFetching: Boolean = false,
    val error: Error? = null
)