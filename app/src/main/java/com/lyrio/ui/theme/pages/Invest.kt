package com.lyrio.ui.theme.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.theme.components.AppButton
import com.lyrio.ui.theme.components.AppWindow
import com.lyrio.ui.theme.components.BarChart
import com.lyrio.ui.theme.components.BarChartData
import com.lyrio.ui.theme.components.eyeIconPainter
import com.lyrio.ui.theme.components.eyeOffIconPainter
import com.lyrio.ui.theme.styles.Green
import com.lyrio.ui.theme.styles.LyrioTheme


@Preview(showBackground = true)
@Composable
fun Invest() {
    var showBalance by remember { mutableStateOf(true) }

    LyrioTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(
                title = "Dinero invertido",
                modifier = Modifier.fillMaxWidth(0.95f).padding(bottom = 16.dp),
            ) {
                Column(

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 24.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Row(
                            modifier = Modifier.weight(1f),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = if (showBalance) "$120367" else "****",
                                fontWeight = FontWeight.Bold,
                                fontSize = 38.sp,
                                modifier = Modifier.padding(end = 4.dp),
                            )
                            if (showBalance) {
                                Text(
                                    text = "58",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp,
                                    modifier = Modifier.padding(top = 2.dp)
                                )
                            }
                        }
                        IconButton(onClick = { showBalance = !showBalance }) { // Cambiar estado
                            Icon(
                                painter = if (showBalance) eyeIconPainter() else eyeOffIconPainter(),
                                contentDescription = if (showBalance) "Ocultar saldo" else "Mostrar saldo",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        AppButton(text = "Invertir", onClick = { /* TODO */ }, modifier = Modifier.weight(1f))
                        AppButton(text = "Retirar", onClick = { /* TODO */ }, modifier = Modifier.weight(1f))
                    }
                }
            }

            AppWindow(
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                title = "Ganancias"
            ){
                Row(
                    modifier = Modifier.padding(vertical = 16.dp).padding(start = 2.dp)
                ) {
                    Text(
                        text = "Este mes: ",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                    Text(
                        text = "$${investData[5].expense}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Green
                    )
                }

                BarChart(investData)
            }
        }
    }
}

val investData = listOf(
    BarChartData("Jun", 250f),
    BarChartData("Jul", 100f),
    BarChartData("Ago", 200f),
    BarChartData("Sep", 150f),
    BarChartData("Oct", 300f),
    BarChartData("Nov", 50f),
)