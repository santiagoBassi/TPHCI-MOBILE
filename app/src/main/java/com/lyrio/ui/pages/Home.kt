package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CircularIconButton
import com.lyrio.ui.components.TransferItem
import com.lyrio.ui.components.eyeIconPainter
import com.lyrio.ui.components.eyeOffIconPainter
import com.lyrio.ui.styles.LightGray
import java.util.Calendar
import java.util.Date

@Composable
fun transferIconPainter(): Painter = painterResource(id = R.drawable.transfer)

@Composable
fun receiveIconPainter(): Painter = painterResource(id = R.drawable.receive)

@Composable
fun cvuAliasIconPainter(): Painter = painterResource(id = R.drawable.cvu_alias)

data class TransferData(
    val transactionType: Int,
    val amount: Double,
    val recipient: String,
    val date: Date
)

@Preview(showBackground = true)
@Composable
fun Home(
    navigateTransfer1: () -> Unit = {},
    navigateReceiveMoney: () -> Unit = {},
    navigateProfile: () -> Unit = {},
    navigateMovements: () -> Unit = {},
    navigateMoney: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Row(
                modifier = Modifier.fillMaxSize().padding(20.dp,25.dp,55.dp,15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                HomeContent(
                    navigateTransfer1,
                    navigateReceiveMoney,
                    navigateProfile,
                    navigateMovements,
                    navigateMoney
                )
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HomeContent(
                    navigateTransfer1,
                    navigateReceiveMoney,
                    navigateProfile,
                    navigateMovements,
                    navigateMoney
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    navigateTransfer1: () -> Unit = {},
    navigateReceiveMoney: () -> Unit = {},
    navigateProfile: () -> Unit = {},
    navigateMovements: () -> Unit = {},
    navigateMoney: () -> Unit = {}
) {
    val transfers = listOf(
        TransferData(R.string.received, 1234.56, "Juan Pérez", Date()),
        TransferData(
            R.string.sent,
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
            R.string.received,
            345.67,
            "Pedro López",
            Calendar.getInstance().apply {
                add(Calendar.DAY_OF_MONTH, -5) // Resta 5 días al día actual
            }.time
        ),
        TransferData(
            R.string.sent,
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

    AppWindow(
        title = stringResource(id = R.string.money),
        showChevron = true,
        onChevronClick = { navigateMoney() },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .widthIn(max = 400.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .padding(bottom = 25.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
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
                    color = Color.Black
                )
                if (showBalance) {
                    Text(
                        text = "58",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 2.dp),
                        color = Color.Black
                    )
                }
            }
            IconButton(onClick = { showBalance = !showBalance }) { // Cambiar estado
                Icon(
                    painter = if (showBalance) eyeIconPainter() else eyeOffIconPainter(),
                    contentDescription = if (showBalance) "Ocultar saldo" else "Mostrar saldo",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            CircularIconButton(icon = transferIconPainter(), text = stringResource(R.string.transfer), onClickFn = navigateTransfer1)
            CircularIconButton(icon = receiveIconPainter(), text = stringResource(R.string.receive), onClickFn = navigateReceiveMoney)
            CircularIconButton(icon = cvuAliasIconPainter(), text = stringResource(R.string.cvu_alias), onClickFn = navigateProfile)
        }
    }
    AppWindow(
        title = stringResource(R.string.movements),
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = 450.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (false) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(R.string.no_movements),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp),
                        color = LightGray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .weight(1f),
                ) {
                    items(transfers.take(4)) { transferData ->
                        TransferItem(
                            transactionType = stringResource(transferData.transactionType),
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
                        stringResource(R.string.see_all_movements),
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 4.dp).clickable { navigateMovements() },
                        color = Color.Gray,
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
