package com.lyrio.ui

import com.lyrio.data.model.Card
import com.lyrio.data.model.User

data class UiState(
    val isAuthenticated: Boolean = false,
    val isFetching: Boolean = false,
    val currentUser: User? = null,
    val cards: List<Card>? = null,
    val currentCard: Card? = null,
    val error: Error? = null
)

val UiState.canGetCurrentUser: Boolean get() = isAuthenticated
val UiState.canGetAllCards: Boolean get() = isAuthenticated
val UiState.canAddCard: Boolean get() = isAuthenticated
val UiState.canDeleteCard: Boolean get() = isAuthenticated && currentCard != null
