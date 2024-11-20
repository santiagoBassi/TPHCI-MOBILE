package com.lyrio.api.network

import com.lyrio.api.models.Payment
import com.lyrio.api.models.User
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://localhost:8080/api/"

private val httpLoggingInterceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(httpLoggingInterceptor)
    .build()

private val json = Json { ignoreUnknownKeys = true }

private val retrofit = Retrofit.Builder()
    .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .client(okHttpClient)
    .build()

interface LyrioApiService {
    @GET("payment")
    suspend fun getPayments(): Array<Payment>

    @GET("user")
    suspend fun getUser(): User

}

object LyrioApi {
    val retrofitService: LyrioApiService by lazy {
        retrofit.create(LyrioApiService::class.java)
    }
}