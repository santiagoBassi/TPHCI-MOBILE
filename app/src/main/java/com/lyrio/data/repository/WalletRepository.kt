package com.lyrio.data.repository

import com.lyrio.data.model.Card
import com.lyrio.data.model.DailyReturn
import com.lyrio.data.model.WalletDetails
import com.lyrio.data.network.WalletRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletRepository(private val remoteDataSource: WalletRemoteDataSource) {
    private val cardsMutex = Mutex()
    private var cachedCards: List<Card> = emptyList()

    suspend fun getBalance(): Double {
        return remoteDataSource.getBalance().balance
    }

    suspend fun deposit(amount: Double): Double {
        return remoteDataSource.deposit(amount).newBalance
    }

    suspend fun getInvestment(): Double {
        return remoteDataSource.getInvestment().investment
    }

    suspend fun invest(amount: Double): WalletDetails {
        return remoteDataSource.invest(amount).asModel()
    }

    suspend fun divest(amount: Double): WalletDetails {
        return remoteDataSource.divest(amount).asModel()
    }

    suspend fun getCards(refresh: Boolean = false): List<Card> {
        if (refresh || cachedCards.isEmpty()) {
            val result = remoteDataSource.getCards()
            cardsMutex.withLock {
                cachedCards = result.map { it.asModel() }
            }
        }
        return cardsMutex.withLock { this.cachedCards }
    }

    suspend fun addCard(card: Card): Card {
        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
        cardsMutex.withLock {
            cachedCards = emptyList()
        }
        return newCard
    }

    suspend fun deleteCard(cardId: Int) {
        remoteDataSource.deleteCard(cardId)
        cardsMutex.withLock {
            cachedCards = emptyList()
        }
    }

    suspend fun getDailyReturns(): List<DailyReturn> {
        return remoteDataSource.getDailyReturns().map { it.asModel() }
    }

    suspend fun getDailyInterest(): Double {
        return remoteDataSource.getDailyInterest().interest
    }

    suspend fun updateAlias(alias: String): WalletDetails {
        return remoteDataSource.updateAlias(alias).asModel()
    }

    suspend fun getDetails(): WalletDetails {
        return remoteDataSource.getDetails().asModel()
    }

}