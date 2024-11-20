package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.lyrio.ui.theme.AddCardSuccessful
import com.lyrio.ui.theme.AddCreditCard
import com.lyrio.ui.theme.ChangeAlias
import com.lyrio.ui.theme.ChangeAliasSuccesful
import com.lyrio.ui.theme.CreditCards
import com.lyrio.ui.theme.LyrioTheme
import com.lyrio.ui.theme.Home
import com.lyrio.ui.theme.Money
import com.lyrio.ui.theme.Movements
import com.lyrio.ui.theme.NavigationDrawer
import com.lyrio.ui.theme.OffWhite
import com.lyrio.ui.theme.Paylink
import com.lyrio.ui.theme.Profile
import com.lyrio.ui.theme.ReceiveMoney
import com.lyrio.ui.theme.Transfer1
import com.lyrio.ui.theme.Transfer2
import com.lyrio.ui.theme.TransferSuccessful


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyrioTheme {
                NavigationDrawer{
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .background(OffWhite)
                    ){
                        Movements()
                    }
                }
            }
        }
    }
}

