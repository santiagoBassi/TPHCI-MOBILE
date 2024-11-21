package com.lyrio.ui.theme.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen(val route: String) {

    // Auth Screens
    @Serializable
    object SignIn : Screen("sign_in")

    @Serializable
    object SignUp1 : Screen("sign_up1")

    @Serializable
    object SignUp2 : Screen("sign_up2")

    @Serializable
    object RecoverPass1 : Screen("recover_pass1")

    @Serializable
    object RecoverPass2 : Screen("recover_pass2")

    @Serializable
    object RecoverPass3 : Screen("recover_pass3")

    // Pages
    @Serializable
    object Landing : Screen("landing")

    @Serializable
    object Home : Screen("home")

    @Serializable
    object AddCardSuccessful : Screen("add_card_successful")

    @Serializable
    object AddCreditCard : Screen("add_credit_card")

    @Serializable
    object AddInvestment : Screen("add_investment")

    @Serializable
    object ChangeAlias : Screen("change_alias")

    @Serializable
    object ChangeAliasSuccessful : Screen("change_alias_successful")

    @Serializable
    object CreditCards : Screen("credit_cards")

    @Serializable
    object Invest : Screen("invest")

    @Serializable
    object Money : Screen("money")

    @Serializable
    object Movements : Screen("movements")

    @Serializable
    object Profile : Screen("profile")

    @Serializable
    object ReceiveMoney : Screen("receive_money")

    @Serializable
    object Transfer1 : Screen("transfer1")

    @Serializable
    object Transfer2 : Screen("transfer2")

    @Serializable
    object TransferSuccessful : Screen("transfer_successful")

    @Serializable
    object WithdrawInvestment : Screen("withdraw_investment")
}
