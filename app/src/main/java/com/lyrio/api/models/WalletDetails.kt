package com.lyrio.api.models

import kotlinx.serialization.Serializable

@Serializable
data class WalletDetails(
    val id: Int,
    val balance: Double,
    val invested: Double,
    val cbu: String,
    val alias: String,
    val createdAt: String,
    val updatedAt: String
)
