package com.lyrio.data.network.model

import com.lyrio.data.model.Payment
import com.lyrio.data.model.PaymentType
import kotlinx.serialization.Serializable

@Serializable
data class NetworkPayment(
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val receiverBalanceBefore: Double,
    val receiverBalanceAfter: Double,
    val pending: Boolean,
    val linkUuid: String?,
    val createdAt: String,
    val updatedAt: String?,
    val card: NetworkCard?,
    val payer: NetworkUser,
    val receiver: NetworkUser
) {
    fun asModel(): Payment {
        return Payment(
            id = id,
            type = when (type) {
                "BALANCE" -> PaymentType.BALANCE
                "CARD" -> PaymentType.CARD
                else -> PaymentType.LINK
            },
            amount = amount,
            pending = pending,
            linkUuid = linkUuid,
            createdAt = createdAt,
            card = card?.asModel(),
            payerName = payer.firstName + payer.lastName,
            receiverName = receiver.firstName + receiver.lastName,
            payerEmail = payer.email,
            receiverEmail = receiver.email,
        )
    }
}

@Serializable
data class TransferNetworkUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
)


@Serializable
data class PaymentsResponse(
    val payments: List<NetworkPayment>
)
