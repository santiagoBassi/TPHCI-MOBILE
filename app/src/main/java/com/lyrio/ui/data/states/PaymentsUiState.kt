package com.lyrio.ui.data.states

import com.lyrio.data.model.Error
import com.lyrio.data.model.PaymentResponse

data class PaymentsUiState(
    val selectedPaymentMethod: Int = 0,
    val lastTransfers: List<PaymentResponse> = emptyList(),
    val transfers: List<PaymentResponse> = emptyList(),
    val receiver: String = "",
    val isFetching: Boolean = false,
    val expensesByMonth: List<Expense> = emptyList(),
    val error: Error? = null
)

data class Expense(
    val month: Int,
    val amount: Double
)