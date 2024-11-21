package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkCard
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class Card(
    val id: Int? = null,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val cvv: String?,
    val type: CardType,
    val createdAt: Date? = null,
    val updatedAt: Date? = null
) {
    fun asNetworkModel(): NetworkCard {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))

        return NetworkCard(
            id = id,
            number = number,
            expirationDate = expirationDate,
            fullName = fullName,
            cvv = cvv,
            type = when (type) { CardType.DEBIT -> "DEBIT" else -> "CREDIT" },
            createdAt = createdAt?.let { dateFormat.format(createdAt!!) },
            updatedAt = updatedAt?.let { dateFormat.format(updatedAt!!) }
        )
    }
}
