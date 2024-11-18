package com.lyrio.ui.theme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true)
@Composable
fun ChangeAliasSuccesful() {
    val fakeAlias = "mi.nuevo.alias"

    Successful(
        message = "¡Cambiaste tu alias!",
        buttonLabel = "Volver al inicio",
        content = {
            Text("Tu nuevo alias es: $fakeAlias")
        }
    )

}