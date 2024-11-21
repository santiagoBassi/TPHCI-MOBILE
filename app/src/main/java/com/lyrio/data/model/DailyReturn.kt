package com.lyrio.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyReturn(
    val id: Int,
    val returnGiven: Double,
    val balanceBefore: Double,
    val balanceAfter: Double,
    val createdAt: String,
    val updatedAt: String
)
