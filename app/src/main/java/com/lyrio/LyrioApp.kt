package com.lyrio

import android.app.Application
import com.lyrio.data.network.PaymentRemoteDataSource
import com.lyrio.data.network.UserRemoteDataSource
import com.lyrio.data.network.WalletRemoteDataSource
import com.lyrio.data.network.api.RetrofitClient
import com.lyrio.data.repository.PaymentRepository
import com.lyrio.data.repository.UserRepository
import com.lyrio.data.repository.WalletRepository

class LyrioApp : Application() {

    private val userRemoteDataSource: UserRemoteDataSource
        get() = UserRemoteDataSource(sessionManager, RetrofitClient.getUserApiService(this))

    private val walletRemoteDataSource: WalletRemoteDataSource
        get() = WalletRemoteDataSource(RetrofitClient.getWalletApiService(this))

    private val paymentRemoteDataSource: PaymentRemoteDataSource
        get() = PaymentRemoteDataSource(RetrofitClient.getPaymentApiService(this))

    val sessionManager: SessionManager
        get() = SessionManager(this)

    val userRepository: UserRepository
        get() = UserRepository(userRemoteDataSource)

    val walletRepository: WalletRepository
        get() = WalletRepository(walletRemoteDataSource)

    val paymentRepository: PaymentRepository
        get() = PaymentRepository(paymentRemoteDataSource)
}