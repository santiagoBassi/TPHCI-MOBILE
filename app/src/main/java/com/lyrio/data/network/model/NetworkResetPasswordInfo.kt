package com.lyrio.data.network.model

import kotlinx.serialization.Serializable

@Serializable
class NetworkResetPasswordInfo (
    val code: String,
    val password: String
)
