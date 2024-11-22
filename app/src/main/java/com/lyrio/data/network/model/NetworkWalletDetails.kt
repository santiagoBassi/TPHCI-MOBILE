package com.lyrio.data.network.model

import com.lyrio.data.model.WalletDetails
import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.Locale

@Serializable
class NetworkWalletDetails(
    val id: Int,
    val balance: Double,
    val invested: Double,
    val cbu: String,
    val alias: String,
    val createdAt: String?,
    val updatedAt: String?
) {
    fun asModel(): WalletDetails {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault(Locale.Category.FORMAT))
        return WalletDetails(
            id = id,
            balance = balance,
            invested = invested,
            cbu = cbu,
            alias = alias,
        )
    }
}
