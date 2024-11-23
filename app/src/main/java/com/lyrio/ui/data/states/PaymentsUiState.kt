package com.lyrio.ui.data.states

import com.lyrio.data.model.Error
import com.lyrio.data.model.Payment

data class PaymentsUiState(
    val selectedPaymentMethod: Int = 0,
    val lastTransfers: List<Payment> = emptyList(),
    val isFetching: Boolean = false,
    val error: Error? = null
)