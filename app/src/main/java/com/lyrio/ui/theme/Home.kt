package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import java.util.Calendar
import java.util.Date

@Composable
fun transferIconPainter(): Painter = painterResource(id = R.drawable.transfer)

@Composable
fun receiveIconPainter(): Painter = painterResource(id = R.drawable.receive)

@Composable
fun cvuAliasIconPainter(): Painter = painterResource(id = R.drawable.cvu_alias)

data class TransferData(
    val transactionType: String,
    val amount: Double,
    val recipient: String,
    val date: Date
)

@Preview(showBackground = true)
@Composable
fun Home() {
    val transfers = listOf(
        TransferData("Recibiste", 1234.56, "Juan Pérez", Date()),
        TransferData(
            "Enviaste",
            -567.89,
            "María García",
            Calendar.getInstance().apply {
                add(
                    Calendar.HOUR_OF_DAY,
                    -3
                ) // Resta 3 horas a la hora actual
            }.time
        ),
        TransferData(
            "Recibiste",
            345.67,
            "Pedro López",
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_MONTH, -5) // Resta 5 días al día actual
            }.time
        ),
        TransferData(
            "Enviaste",
            -567.89,
            "María García",
            Calendar.getInstance().apply {
                add(
                    Calendar.HOUR_OF_DAY,
                    -3
                ) // Resta 3 horas a la hora actual
            }.time
        )
    )

    var showBalance by remember { mutableStateOf(true) } // Estado para mostrar/ocultar

    LyrioTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(title = "Dinero", showChevron = true, modifier = Modifier.padding(bottom = 16.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp).padding(bottom = 25.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
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
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ){
                    CircularIconButton(icon = transferIconPainter(), text = "Transferir")
                    CircularIconButton(icon = receiveIconPainter(), text = "Recibir")
                    CircularIconButton(icon = cvuAliasIconPainter(), text = "CVU y Alias")
                }
            }
            AppWindow(
                title = "Movimientos",
                modifier = Modifier.fillMaxHeight().weight(1f),
                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (false) {
                        Column(
                            modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No hay movimientos disponibles",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp),
                                color = LightGray
                            )
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier.padding(vertical = 10.dp).weight(1f),
                        ) {
                            items(transfers.take(4)) { transferData ->
                                TransferItem(
                                    transactionType = transferData.transactionType,
                                    amount = transferData.amount,
                                    recipient = transferData.recipient,
                                    date = transferData.date
                                )
                            }
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                "Ver todos mis movimientos ",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 4.dp),
                                color = Color.Gray
                            )
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Chevron",
                                tint = Color.Gray,
                            )
                        }
                    }
                }
            }
        }
    }
}
