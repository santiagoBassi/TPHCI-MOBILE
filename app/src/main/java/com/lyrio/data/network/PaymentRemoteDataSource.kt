package com.lyrio.data.network

import com.lyrio.data.network.api.PaymentApiService

class PaymentRemoteDataSource(
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {
}