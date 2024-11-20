package com.lyrio.api.models

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
