package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun TransferSuccessful() {
    val amount = 1000

    Successful(message = "¡Transferencia enviada!", buttonLabel = "Volver al Inicio", variant = "secondary"){
        Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Text("a Ezequiel Testoni", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        AppButton(text = "Nueva transferencia", onClick = { /* TODO */ }, width = 0.8f)
    }

}