package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun AddCardSuccessful() {
    Successful(
        message = "¡Tarjeta agregada!",
        buttonLabel = "Volver al Inicio",
        variant = "secondary"
    ) {
        Spacer(Modifier.height(80.dp))
        AppButton(text = "Agregar otra tarjeta", onClick = {}, width = 0.8f)
    }
}