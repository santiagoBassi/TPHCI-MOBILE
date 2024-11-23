package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkPayment

data class Payment(
    val id: Int,
    val type: PaymentType,
    val amount: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val card: Card? = null,
    val payerName: String,
    val receiverName: String,
    val payerEmail: String,
    val receiverEmail: String
)
