package com.lyrio.data.network
//
//import com.lyrio.SessionManager
//import com.lyrio.data.model.User
//import com.lyrio.data.network.api.UserApiService
//import com.lyrio.data.network.model.NetworkCredentials
//import com.lyrio.data.network.model.NetworkEmail
//import com.lyrio.data.network.model.NetworkResetPasswordInfo
//import com.lyrio.data.network.model.NetworkToken
//import com.lyrio.data.network.model.NetworkUser
//import com.lyrio.data.network.model.NetworkVerificationCode
//import retrofit2.Response
//import retrofit2.http.Body
//import retrofit2.http.GET
//import retrofit2.http.POST
//
//class UserRemoteDataSource(
//    private val sessionManager: SessionManager,
//    private val userApiService: UserApiService
//) : RemoteDataSource() {
//    suspend fun register(firstName: String, lastName: String, birthDate: String, email: String, password: String): NetworkUser {
//        return handleApiResponse { userApiService.register(NetworkUser(null, firstName, lastName, birthDate, email, password)) }
//    }
//
//    suspend fun getCurrentUser(): NetworkUser {
//        return handleApiResponse { userApiService.getCurrentUser() }
//    }
//
//    suspend fun login(username: String, password: String) {
//        val response = handleApiResponse {
//            userApiService.login(NetworkCredentials(username, password))
//        }
//        sessionManager.saveAuthToken(response.token)
//    }
//
//    suspend fun verifyEmail(code: String): NetworkUser {
//        return handleApiResponse { userApiService.verifyEmail(NetworkVerificationCode(code)) }
//    }
//
//    suspend fun recoverPassword(email: String) {
//        handleApiResponse { userApiService.recoverPassword(NetworkEmail(email)) }
//    }
//
//    suspend fun resetPassword(code: String, password: String) {
//        handleApiResponse { userApiService.resetPassword(NetworkResetPasswordInfo(code, password)) }
//    }
//
//    suspend fun logout() {
//        handleApiResponse { userApiService.logout() }
//        sessionManager.removeAuthToken()
//    }
//}