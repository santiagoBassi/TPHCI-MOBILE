package com.lyrio.ui.theme.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.theme.components.AppButton
import com.lyrio.ui.theme.components.AppInput
import com.lyrio.ui.theme.components.AppWindow
import com.lyrio.ui.theme.styles.Orange

@Preview(showBackground = true)
@Composable
fun WithdrawInvestment() {

    var amount by remember { mutableLongStateOf(0) }
    val availableBalance = 100000.0

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AppWindow {
            Column(
                modifier = Modifier.fillMaxHeight(0.8f),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "¿Cuánto querés dejar",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        "de invertir?",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
                Text("Dinero invertido: $$availableBalance", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp), fontSize = 18.sp)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Orange)
                    Box(
                        modifier = Modifier.fillMaxWidth(0.85f),
                        contentAlignment = Alignment.Center
                    ) {
                        AppInput(
                            value = if (amount.toInt() == 0) "" else amount.toString(),
                            onValueChange = { amount = it.toLongOrNull() ?: 0 },
                            label = "Monto",
                            hint = "Nota: Recordá que podés volver a invertir este dinero cuando quieras.",
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                AppButton(text = "Dejar de invertir", onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.7f))
            }
        }
    }

}