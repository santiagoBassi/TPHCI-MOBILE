package com.lyrio.data.network.model

import com.lyrio.data.model.User
import kotlinx.serialization.Serializable

@Serializable
class NetworkUser(
    val id: Int? = null,
    val firstName: String = "",
    val lastName: String = "",
    val birthDate: String = "",
    val email: String = "",
    val password: String? = null
) {
    fun asModel(): User {
        return User(
            id = id,
            firstName = firstName,
            lastName = lastName,
            birthDate = birthDate,
            email = email,
            password = password
        )
    }
}
