package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lyrio.ui.theme.layout.DefaultLayout
import com.lyrio.ui.theme.pages.AddCardSuccessful
import com.lyrio.ui.theme.pages.AddCreditCard
import com.lyrio.ui.theme.pages.AddInvestment
import com.lyrio.ui.theme.pages.ChangeAliasSuccesful
import com.lyrio.ui.theme.pages.CreditCards
import com.lyrio.ui.theme.pages.Home
import com.lyrio.ui.theme.pages.Invest
import com.lyrio.ui.theme.pages.LandingPage
import com.lyrio.ui.theme.pages.Money
import com.lyrio.ui.theme.pages.Movements
import com.lyrio.ui.theme.pages.Paylink
import com.lyrio.ui.theme.pages.Transfer1
import com.lyrio.ui.theme.pages.Transfer2
import com.lyrio.ui.theme.pages.TransferSuccessful
import com.lyrio.ui.theme.pages.WithdrawInvestment
import com.lyrio.ui.theme.styles.LyrioTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyrioTheme {
                DefaultLayout {
                    Paylink(context = this@MainActivity)
                }
            }
        }
    }
}

