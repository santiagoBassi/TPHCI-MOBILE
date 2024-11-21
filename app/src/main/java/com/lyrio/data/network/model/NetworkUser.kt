package com.lyrio.data.network.model

import com.lyrio.data.model.User
import kotlinx.serialization.Serializable

@Serializable
class NetworkUser (
    var id: Int?,
    var firstName: String,
    var lastName: String,
    var birthDate: String,
    var email: String,
    var password: String?,
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
