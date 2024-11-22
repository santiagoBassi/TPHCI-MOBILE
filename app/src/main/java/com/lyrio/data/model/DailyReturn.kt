package com.lyrio.data.model

import com.lyrio.data.network.model.NetworkDailyReturn

data class DailyReturn(
    val id: Int,
    val returnGiven: Double,
) {
    fun asNetworkModel(): NetworkDailyReturn {
        return NetworkDailyReturn(
            id = id,
            returnGiven = returnGiven,
            balanceBefore = null,
            balanceAfter = null,
            createdAt = null,
            updatedAt = null
        )
    }

}
