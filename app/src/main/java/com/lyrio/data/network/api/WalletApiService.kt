package com.lyrio.data.network.api

import com.lyrio.data.network.model.NetworkAlias
import com.lyrio.data.network.model.NetworkAmount
import com.lyrio.data.network.model.NetworkBalance
import com.lyrio.data.network.model.NetworkCard
import com.lyrio.data.network.model.NetworkDailyInterest
import com.lyrio.data.network.model.NetworkDailyReturn
import com.lyrio.data.network.model.NetworkInvestmentAmount
import com.lyrio.data.network.model.NetworkNewBalance
import com.lyrio.data.network.model.NetworkNewCard
import com.lyrio.data.network.model.NetworkWalletDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WalletApiService {
    @GET("wallet/balance")
    suspend fun getBalance(): Response<NetworkBalance>

    @POST("wallet/recharge")
    suspend fun deposit(@Body amount: NetworkAmount): Response<NetworkNewBalance>

    @GET("wallet/investment")
    suspend fun getInvestment(): Response<NetworkInvestmentAmount>

    @POST("wallet/invest")
    suspend fun invest(@Body amount: NetworkAmount): Response<NetworkWalletDetails>

    @POST("wallet/divest")
    suspend fun divest(@Body amount: NetworkAmount): Response<NetworkWalletDetails>

    @GET("wallet/cards")
    suspend fun getCards(): Response<List<NetworkCard>>

    @POST("wallet/cards")
    suspend fun addCard(@Body card: NetworkNewCard): Response<NetworkCard>

    @DELETE("wallet/cards/{cardId}")
    suspend fun deleteCard(@Path("cardId") cardId: Int): Response<Unit>

    @GET("wallet/daily-returns")
    suspend fun getDailyReturns(): Response<List<NetworkDailyReturn>>

    @GET("wallet/daily-interest")
    suspend fun getDailyInterest(): Response<NetworkDailyInterest>

    @PUT("wallet/update-alias")
    suspend fun updateAlias(@Body alias: NetworkAlias): Response<NetworkWalletDetails>

    @GET("wallet/details")
    suspend fun getDetails(): Response<NetworkWalletDetails>

}