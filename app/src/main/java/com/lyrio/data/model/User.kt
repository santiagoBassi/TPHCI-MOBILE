package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkUser

data class User(
    val id: Int?,
    val firstName: String,
    val lastName: String,
    val birthDate: String,
    val email: String,
    val password: String?
) {
    fun asNetworkModel(): NetworkUser {
        return NetworkUser(
            id = id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            email = email,
            password = password
        )
    }
}
