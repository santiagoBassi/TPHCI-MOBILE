package com.lyrio.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun ChangeAlias() {
    var newAlias by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        AppWindow(
            modifier = Modifier.fillMaxWidth(fraction = 0.95f).fillMaxHeight(fraction = 0.85f)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Ingresá tu nuevo alias", style = MaterialTheme.typography.titleLarge
                )
                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    AppInput(value = newAlias, onValueChange = { newAlias = it }, label = "Nuevo alias", modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("- Debe tener entre 6 y 20 caracteres")
                    Text("- No uses la letra ñ")
                    Text("- Los únicos caracteres especiales permitidos")
                    Text("  son  '_'  y  '.'")
                }
                AppButton(text = "Cambiar alias", onClick = { /* TODO */ }, width = 0.8f)
            }

        }
    }
}