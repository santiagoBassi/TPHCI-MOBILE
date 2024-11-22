package com.lyrio.data.network.model

import com.lyrio.data.model.DailyReturn
import kotlinx.serialization.Serializable

@Serializable

class NetworkDailyReturn(
    val id: Int,
    val returnGiven: Double,
    val balanceBefore: Double?,
    val balanceAfter: Double?,
    val createdAt: String?,
    val updatedAt: String?
) {
    fun asModel(): DailyReturn {
        return DailyReturn(
            id = id,
            returnGiven = returnGiven
        )
    }
}
