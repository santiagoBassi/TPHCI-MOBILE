package com.lyrio.data.repository

import com.lyrio.data.model.User
import com.lyrio.data.network.UserRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class UserRepository(private val remoteDataSource: UserRemoteDataSource) {
    private val currentUserMutex = Mutex()
    private var currentUser: User? = null

    suspend fun register(firstName: String, lastName: String, birthDate: String, email: String, password: String): User {
        val result = remoteDataSource.register(firstName, lastName, birthDate, email, password)
        return result.asModel()
    }

    suspend fun getCurrentUser(refresh: Boolean = false): User? {
        if (refresh || currentUser == null) {
            val result = remoteDataSource.getCurrentUser()
            currentUserMutex.withLock {
                currentUser = result.asModel()
            }
        }
        return currentUserMutex.withLock { this.currentUser }
    }

    suspend fun login(username: String, password: String) {
        remoteDataSource.login(username, password)
    }

    suspend fun verifyEmail(code: String): User {
        val result = remoteDataSource.verifyEmail(code)
        return result.asModel()
    }

    suspend fun recoverPassword(email: String) {
        remoteDataSource.recoverPassword(email)
    }

    suspend fun resetPassword(code: String, password: String) {
        remoteDataSource.resetPassword(code, password)
    }

    suspend fun logout() {
        remoteDataSource.logout()
    }


}