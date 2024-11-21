package com.lyrio.ui.theme.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.ui.theme.components.Successful
import com.lyrio.ui.theme.components.AppButton

@Preview(showBackground = true)
@Composable
fun TransferSuccessful() {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp,20.dp,100.dp,15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TransferSuccessfulContent(height = 1f)
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TransferSuccessfulContent()
            }
        }
    }
}

@Composable
fun TransferSuccessfulContent(height: Float = 0.5f) {
    val amount = 1000

    Successful(message = "¡Transferencia enviada!", buttonLabel = "Volver al Inicio", variant = "secondary", height = height){
        Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Text("a Ezequiel Testoni", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        AppButton(text = "Nueva transferencia", onClick = { /* TODO */ }, width = if(height == 1f) 0.6f else 0.8f)
    }
}