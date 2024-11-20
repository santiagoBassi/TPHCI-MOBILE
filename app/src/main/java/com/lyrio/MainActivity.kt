package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.lyrio.ui.theme.styles.LyrioTheme
import com.lyrio.ui.theme.pages.Movements
import com.lyrio.ui.theme.layout.NavigationDrawer
import com.lyrio.ui.theme.pages.AddInvestment
import com.lyrio.ui.theme.pages.Invest
import com.lyrio.ui.theme.pages.WithdrawInvestment
import com.lyrio.ui.theme.styles.OffWhite


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
                        WithdrawInvestment()
                    }
                }
            }
        }
    }
}

