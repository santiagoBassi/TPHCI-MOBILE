package com.lyrio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkCredentials(
    val email: String,
    val password: String
)
