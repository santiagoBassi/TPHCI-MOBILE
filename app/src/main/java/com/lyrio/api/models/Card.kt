package com.lyrio.api.models

import kotlinx.serialization.Serializable

@Serializable
data class Card(
    val id: Int,
    val number: String,
    val expirationDate: String,
    val fullName: String,
    val type: String,
    val createdAt: String,
    val updatedAt: String
)
