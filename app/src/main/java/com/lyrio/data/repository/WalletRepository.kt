package com.lyrio.data.repository

import com.lyrio.data.model.Card
import com.lyrio.data.network.WalletRemoteDataSource
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class WalletRepository(private val remoteDataSource: WalletRemoteDataSource) {
    private val cardsMutex = Mutex()
    private var cachedCards: List<Card> = emptyList()

//    suspend fun getCards(refresh: Boolean = false): List<Card> {
//        if (refresh || cachedCards.isEmpty()) {
//            val result = remoteDataSource.getCards()
//            cardsMutex.withLock {
//                cachedCards = result.map { it.asModel() }
//            }
//        }
//        return cardsMutex.withLock { this.cachedCards }
//    }
//
//    suspend fun addCard(card: Card): Card {
//        val newCard = remoteDataSource.addCard(card.asNetworkModel()).asModel()
//        cardsMutex.withLock {
//            cachedCards = emptyList()
//        }
//        return newCard
//    }
//
//    suspend fun deleteCard(cardId: Int) {
//        remoteDataSource.deleteCard(cardId)
//        cardsMutex.withLock {
//            cachedCards = emptyList()
//        }
//    }
}