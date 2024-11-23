package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkPaymentRequest

data class PaymentRequest(
    val amount: Double,
    val description: String,
    val type: PaymentType,
    val cardId: Int?,
    val receiverEmail: String
) {
    fun asNetworkModel(): NetworkPaymentRequest {
        return NetworkPaymentRequest(
            amount = amount,
            description = description,
            type = type.name,
            cardId = cardId,
            receiverEmail = receiverEmail
        )
    }
}
