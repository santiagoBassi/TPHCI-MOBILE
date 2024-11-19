package com.lyrio.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun AddCardSuccessful() {
    LyrioTheme {
        Successful(
            message = "¡Tarjeta agregada!",
            buttonLabel = "Volver al Inicio"
        ){
            AppButton(text = "Agregar otra tarjeta", onClick = {}, width = 0.8f, background = LightGray)
        }
    }
}