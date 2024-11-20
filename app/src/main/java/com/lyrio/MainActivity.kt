package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.lyrio.ui.theme.layout.DefaultLayout
import com.lyrio.ui.theme.pages.Home
import com.lyrio.ui.theme.pages.Invest
import com.lyrio.ui.theme.styles.LyrioTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyrioTheme {
                DefaultLayout{
                    Home()
                }
            }
        }
    }
}

