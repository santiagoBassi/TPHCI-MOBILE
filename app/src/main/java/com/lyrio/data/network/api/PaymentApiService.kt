package com.lyrio.data.network.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PaymentApiService {
//    @POST("payment")
//    suspend fun makePayment(@Body paymentRequest: NetworkPaymentRequest): Response<Unit>
//
//    @GET("payment")
//    suspend fun getPayments(): Response<List<NetworkPayment>>
//    // TODO: Pass params to this function
//
//    @GET("payment/{paymentId}")
//    suspend fun getPaymentById(@Path("paymentId") paymentId: String): Response<NetworkPayment>

    @GET("payment/link/{linkUuid}")
    suspend fun getPaymentByLink(@Path("linkUuid") linkUuid: String)

    @POST("payment/link/{linkUuid}")
    suspend fun payByLink(@Path("linkUuid") linkUuid: String)

}