package com.lyrio.ui.theme

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


@Preview(showBackground = true)
@Composable
fun Money() {
    var showBalance by remember { mutableStateOf(true) } // Estado para mostrar/ocultar

    LyrioTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(
                title = "Dinero",
                modifier = Modifier.fillMaxWidth(0.95f).padding(bottom = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
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
                        if(showBalance) {
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
            }
            AppWindow(
                modifier = Modifier
                    .fillMaxWidth(0.95f),
                title = "Gastos"
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
                        text = "$${expensesData[5].expense}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Red
                    )
                }

                BarChart(expensesData)
            }
        }
    }
}

val expensesData = listOf(
    ExpenseData("Jun", 250f),
    ExpenseData("Jul", 100f),
    ExpenseData("Ago", 200f),
    ExpenseData("Sep", 150f),
    ExpenseData("Oct", 300f),
    ExpenseData("Nov", 50f),
)