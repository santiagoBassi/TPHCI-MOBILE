package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun ChangeAliasSuccesful() {
    val fakeAlias = "mi.nuevo.alias"

    Successful(
        message = "¡Cambiaste tu alias!",
        buttonLabel = "Volver al Perfil",
        content = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp).padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Tu nuevo alias es: ", color = Color.Gray, fontWeight = FontWeight.Medium)
                Text(fakeAlias, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            }
        }
    )

}