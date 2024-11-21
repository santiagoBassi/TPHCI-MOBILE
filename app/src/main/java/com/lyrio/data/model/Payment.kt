package com.lyrio.data.model

import kotlinx.serialization.Serializable
import com.lyrio.data.model.Card

@Serializable
data class Payment(
    val id: Int,
    val type: String,
    val amount: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val pending: Boolean,
    val linkUuid: String,
    val createdAt: String,
    val updatedAt: String,
    //val card: Card? = null
) {

}