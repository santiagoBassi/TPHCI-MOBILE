package com.lyrio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkPaymentRequest(
    val amount: Double,
    val description: String,
    val type: String,
    val cardId: Int?,
    val receiverEmail: String?
)

