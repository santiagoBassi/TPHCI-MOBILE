package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkWalletDetails
import java.util.Date


data class WalletDetails(
    val id: Int,
    val balance: Double,
    val invested: Double,
    val cbu: String,
    val alias: String,
) {
    fun toNetworkModel(): NetworkWalletDetails {
        return NetworkWalletDetails(
            id = id,
            balance = balance,
            invested = invested,
            cbu = cbu,
            alias = alias,
            createdAt = null,
            updatedAt = null
        )
    }
}
