package com.lyrio.ui.data.states

import com.lyrio.data.model.Card
import com.lyrio.data.model.Error

data class WalletUiState(
    val balance: Double = 0.0,
    val investedMoney: Double = 0.0,
    val alias: String = "",
    val cbu: String = "",
    val cards: List<Card> = emptyList(),
    val isFetching: Boolean = false,
    val error: Error? = null
)