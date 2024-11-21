package com.lyrio.data.network.api
//
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
//interface UserApiService {
//    @POST("user")
//    suspend fun register(@Body credentials: NetworkUser): Response<NetworkUser>
//
//    @GET("user")
//    suspend fun getCurrentUser(): Response<NetworkUser>
//
//    @POST("user/login")
//    suspend fun login(@Body credentials: NetworkCredentials): Response<NetworkToken>
//
//    @POST("user/verify")
//    suspend fun verifyEmail(@Body verificationCode: NetworkVerificationCode): Response<NetworkUser>
//
//    @POST("user/recover-password")
//    suspend fun recoverPassword(@Body email: NetworkEmail): Response<Unit>
//
//    @POST("user/reset-password")
//    suspend fun resetPassword(@Body resetPasswordInfo: NetworkResetPasswordInfo): Response<Unit>
//
//    @POST("user/logout")
//    suspend fun logout(): Response<Unit>
//
//}