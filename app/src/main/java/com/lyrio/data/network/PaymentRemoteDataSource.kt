package com.lyrio.data.network

import com.lyrio.data.network.api.PaymentApiService
import com.lyrio.data.network.model.NetworkPayment
import com.lyrio.data.network.model.NetworkPaymentRequest
import com.lyrio.data.network.model.NetworkPaymentsResponse
import com.lyrio.data.network.model.NetworkSuccess

class PaymentRemoteDataSource(
    private val paymentApiService: PaymentApiService
) : RemoteDataSource() {

    suspend fun makePaymentWithBalance(amount: Double, description: String, type: String, receiverEmail: String): Unit {
        return handleApiResponse { paymentApiService.makePayment(NetworkPaymentRequest(amount, description, type, null, receiverEmail)) }
    }

    suspend fun makePaymentWithCard(amount: Double, description: String, type: String, cardId: Int, receiverEmail: String): Unit {
        return handleApiResponse { paymentApiService.makePayment(NetworkPaymentRequest(amount, description, type, cardId, receiverEmail)) }
    }

    suspend fun generatePayLink(amount: Double, description: String, type: String): Unit {
        return handleApiResponse { paymentApiService.makePayment(NetworkPaymentRequest(amount, description, type, null, null)) }
    }

    suspend fun getPayments(): NetworkPaymentsResponse {
        return handleApiResponse { paymentApiService.getPayments() }
    }

    suspend fun getPaymentById(paymentId: String): NetworkPayment {
        return handleApiResponse { paymentApiService.getPaymentById(paymentId) }
    }

    suspend fun getPaymentByLink(linkUuid: String): NetworkPayment {
        return handleApiResponse { paymentApiService.getPaymentByLink(linkUuid) }
    }

    suspend fun payByLink(linkUuid: String): NetworkSuccess {
        return handleApiResponse { paymentApiService.payByLink(linkUuid) }
    }

}