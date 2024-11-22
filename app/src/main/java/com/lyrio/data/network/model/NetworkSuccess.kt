package com.lyrio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkSuccess(
    val success: Boolean
)