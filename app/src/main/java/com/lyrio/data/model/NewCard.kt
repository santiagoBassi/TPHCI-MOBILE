package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkCard
import com.lyrio.data.network.model.NetworkNewCard

class NewCard(
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val cvv: String,
    val type: CardType,
) {
    fun asNetworkModel(): NetworkNewCard {
        return NetworkNewCard(
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            cvv = cvv,
            type = when (type) { CardType.DEBIT -> "DEBIT" else -> "CREDIT" },
        )
    }
}