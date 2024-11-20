package com.lyrio.ui.theme.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.lyrio.R

enum class AppDestinations (
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Filled.Home, R.string.home),
    PROFILE(R.string.profile, Icons.Filled.Person, R.string.profile),
    TRANSFER(R.string.transfer, Icons.Filled.Home, R.string.transfer),
    INVEST(R.string.invest, Icons.Filled.Home, R.string.invest),
    MONEY(R.string.money, Icons.Filled.Home, R.string.money),
    ACTIVITY(R.string.activity, Icons.Filled.Home, R.string.activity),
    PAYLINK(R.string.pay_link, Icons.Filled.Home, R.string.pay_link),
    CARDS(R.string.cards, Icons.Filled.Home, R.string.cards),
    ADD_CARD(R.string.add_card, Icons.Filled.Home, R.string.add_card),
}