package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkPayment

data class Payment(
    val id: Int,
    val type: PaymentType,
    val amount: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val card: Card? = null
) {
    fun asNetworkModel(): NetworkPayment {
        return NetworkPayment(
            id = id,
            type = when (type) { PaymentType.BALANCE -> "BALANCE"; PaymentType.CARD -> "CARD" else -> "LINK" },
            amount = amount,
            balanceBefore = null,
            balanceAfter = null,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            updatedAt = null,
            card = card?.asNetworkModel()
        )
    }
}