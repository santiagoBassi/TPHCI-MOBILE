package com.lyrio.data.network

import com.lyrio.data.network.api.WalletApiService
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

class WalletRemoteDataSource(
    private val walletApiService: WalletApiService
) : RemoteDataSource() {

    suspend fun getBalance(): NetworkBalance {
        return handleApiResponse { walletApiService.getBalance() }
    }

    suspend fun deposit(amount: Double): NetworkNewBalance {
        return handleApiResponse { walletApiService.deposit(NetworkAmount(amount)) }
    }

    suspend fun getInvestment(): NetworkInvestmentAmount {
        return handleApiResponse { walletApiService.getInvestment() }
    }

    suspend fun invest(amount: Double): NetworkWalletDetails {
        return handleApiResponse { walletApiService.invest(NetworkAmount(amount)) }
    }

    suspend fun divest(amount: Double): NetworkWalletDetails {
        return handleApiResponse { walletApiService.divest(NetworkAmount(amount)) }
    }

    suspend fun getCards(): List<NetworkCard> {
        return handleApiResponse { walletApiService.getCards() }
    }

    suspend fun addCard(card: NetworkNewCard): NetworkCard {
        return handleApiResponse { walletApiService.addCard(card) }
    }

    suspend fun deleteCard(cardId: Int) {
        handleApiResponse { walletApiService.deleteCard(cardId) }
    }

    suspend fun getDailyReturns(): List<NetworkDailyReturn> {
        return handleApiResponse { walletApiService.getDailyReturns() }
    }

    suspend fun getDailyInterest(): NetworkDailyInterest {
        return handleApiResponse { walletApiService.getDailyInterest() }
    }

    suspend fun updateAlias(alias: String): NetworkWalletDetails {
        return handleApiResponse { walletApiService.updateAlias(NetworkAlias(alias)) }
    }

    suspend fun getDetails(): NetworkWalletDetails {
        return handleApiResponse { walletApiService.getDetails() }
    }

}