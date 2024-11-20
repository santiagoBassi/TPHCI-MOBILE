package com.lyrio.ui.theme.pages

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.ui.theme.components.AppButton
import com.lyrio.ui.theme.components.Successful

@Preview(showBackground = true)
@Composable
fun AddCardSuccessful() {
    Successful(
        message = "¡Tarjeta agregada!",
        buttonLabel = "Volver al Inicio",
        variant = "secondary"
    ) {
        Spacer(Modifier.height(40.dp))
        AppButton(text = "Agregar otra tarjeta", onClick = {}, width = 0.8f)
    }
}