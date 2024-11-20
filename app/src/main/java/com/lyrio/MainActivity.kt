package com.lyrio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lyrio.ui.theme.layout.DefaultLayout
import com.lyrio.ui.theme.styles.LyrioTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LyrioTheme {
                DefaultLayout{
                    Text("Puto el que lee", modifier = Modifier.background(Color.Red))
                }
            }
        }
    }
}

