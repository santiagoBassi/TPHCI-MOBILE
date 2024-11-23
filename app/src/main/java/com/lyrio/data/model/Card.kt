package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkCard

class Card(
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val cvv: String?,
    val type: CardType,
    val createdAt: String?,
) {
    fun asNetworkModel(): NetworkCard {
        return NetworkCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            cvv = cvv,
            type = when (type) { CardType.DEBIT -> "DEBIT" else -> "CREDIT" },
            createdAt = createdAt,
            updatedAt = null
        )
    }
}
