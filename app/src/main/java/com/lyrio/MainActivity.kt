package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import com.lyrio.ui.theme.LyrioTheme
import com.lyrio.ui.theme.DefaultLayout
import com.lyrio.ui.theme.NavigationDrawer


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyrioTheme {

                NavigationDrawer{
                    Text("Hola mundoooooooooooooooo")
                }
            }
        }
    }
}

