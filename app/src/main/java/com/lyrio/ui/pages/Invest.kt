package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.BarChart
import com.lyrio.ui.components.BarChartData
import com.lyrio.ui.components.eyeIconPainter
import com.lyrio.ui.components.eyeOffIconPainter
import com.lyrio.ui.styles.Green


@Composable
fun Invest(
    navigateAddInvestment: () -> Unit,
    navigateWithdrawInvestment: () -> Unit
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Row(
                modifier = Modifier.fillMaxSize().padding(25.dp,25.dp,75.dp,15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
            ) {
                InvestContent(
                    maxBarHeight =  170.dp,
                    navigateAddInvestment = navigateAddInvestment,
                    navigateWithdrawInvestment = navigateWithdrawInvestment
                )
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InvestContent(
                    navigateAddInvestment = navigateAddInvestment,
                    navigateWithdrawInvestment = navigateWithdrawInvestment
                )
            }
        }
    }
}

@Composable
fun InvestContent(
    maxBarHeight: Dp = 200.dp,
    navigateAddInvestment: () -> Unit,
    navigateWithdrawInvestment: () -> Unit
){
    var showBalance by remember { mutableStateOf(true) }

    AppWindow(
        title = stringResource(R.string.invested_money),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .widthIn(max = 375.dp),
    ) {
        Column {
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                AppButton(text = stringResource(R.string.invest), onClick = navigateAddInvestment, modifier = Modifier.weight(1f))
                AppButton(text = stringResource(R.string.withdraw), onClick = navigateWithdrawInvestment, modifier = Modifier.weight(1f))
            }
        }
    }

    AppWindow(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(375.dp),
        title = stringResource(R.string.earnings)
    ){
        Row(
            modifier = Modifier.padding(vertical = 16.dp).padding(start = 2.dp)
        ) {
            Text(
                text = stringResource(R.string.this_month),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Gray
            )
            Text(
                text = " $${investData[5].expense}",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Green
            )
        }

        BarChart(investData, maxBarHeight)
    }
}

val investData = listOf(
    BarChartData(months[(actualMonth - 6 + months.size) % months.size], 250f),
    BarChartData(months[(actualMonth - 5 + months.size) % months.size], 100f),
    BarChartData(months[(actualMonth - 4 + months.size) % months.size], 200f),
    BarChartData(months[(actualMonth - 3 + months.size) % months.size], 150f),
    BarChartData(months[(actualMonth - 2 + months.size) % months.size], 300f),
    BarChartData(months[(actualMonth - 1 + months.size) % months.size], 50f)
)

