package com.lyrio.data.repository

import com.lyrio.data.model.Payment
import com.lyrio.data.network.PaymentRemoteDataSource

class PaymentRepository(private val remoteDataSource: PaymentRemoteDataSource) {
    suspend fun makePaymentWithBalance(amount: Double, description: String, type: String, receiverEmail: String) {
        remoteDataSource.makePaymentWithBalance(amount, description, type, receiverEmail)
    }

    suspend fun makePaymentWithCard(amount: Double, description: String, type: String, cardId: Int, receiverEmail: String) {
        remoteDataSource.makePaymentWithCard(amount, description, type, cardId, receiverEmail)
    }

    suspend fun generatePayLink(amount: Double, description: String, type: String) {
        remoteDataSource.generatePayLink(amount, description, type)
    }

    suspend fun getPayments(): List<Payment> {
        return remoteDataSource.getPayments().payments.map { it.asModel() }
    }

    suspend fun getPaymentById(paymentId: String): Payment {
        return remoteDataSource.getPaymentById(paymentId).asModel()
    }

    suspend fun getPaymentByLink(linkUuid: String): Payment {
        return remoteDataSource.getPaymentByLink(linkUuid).asModel()
    }

    suspend fun payByLink(linkUuid: String) {
        remoteDataSource.payByLink(linkUuid)
    }

}