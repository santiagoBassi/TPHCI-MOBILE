package com.lyrio.data.network.model

import com.lyrio.data.model.Payment
import com.lyrio.data.model.PaymentType
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPayment (
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double?,
    val balanceAfter: Double?,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String?,
    val card: NetworkCard?,
) {
    fun asModel(): Payment {
        return Payment(
            id = id,
            type = when (type) { "BALANCE" -> PaymentType.BALANCE; "CARD" -> PaymentType.CARD else -> PaymentType.LINK },
            amount = amount,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            card = card?.asModel()
        )
    }
}
