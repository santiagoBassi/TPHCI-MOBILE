package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.lyrio.R
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.BarChart
import com.lyrio.ui.components.BarChartData
import com.lyrio.ui.components.eyeIconPainter
import com.lyrio.ui.components.eyeOffIconPainter
import com.lyrio.ui.data.viewmodels.PaymentsViewModel
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.styles.Red
import com.lyrio.utils.formatCurrencyWhole
import com.lyrio.utils.getDecimalPart
import kotlinx.coroutines.flow.Flow


@Composable
fun Money(
    walletViewModel: WalletViewModel,
    userViewModel: UserViewModel,
    paymentsViewModel: PaymentsViewModel
) {
    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp


    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 0.92f)
                        .fillMaxHeight(if (isTablet) 0.8f else 0.92f),
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    val maxBarHeight = if(isTablet) 0.4 * maxHeight else 0.4 * 0.92 * maxHeight
                    MoneyContent(
                        maxMoneyWidth = if (isTablet) 375.dp else 350.dp,
                        maxBarHeight = maxBarHeight,
                        walletViewModel = walletViewModel,
                        userViewModel = userViewModel,
                        paymentsViewModel = paymentsViewModel
                        )
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 1f)
                        .fillMaxHeight(if (isTablet) 0.6f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val maxBarHeight = if(isTablet) 0.2 * maxHeight else 0.225 * maxHeight
                    MoneyContent(
                        maxBarHeight = maxBarHeight,
                        vertical = true,
                        walletViewModel = walletViewModel,
                        userViewModel = userViewModel,
                        paymentsViewModel = paymentsViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MoneyContent(
    maxMoneyWidth: Dp = 500.dp,
    maxBarHeight: Dp = 200.dp,
    vertical: Boolean = false,
    walletViewModel: WalletViewModel,
    userViewModel: UserViewModel,
    paymentsViewModel : PaymentsViewModel
) {
    var showBalance by remember { mutableStateOf(true) }
    val uiStateWallet by walletViewModel.uiStateWallet.collectAsState()
    val uiStateUser by userViewModel.uiStateUser.collectAsState()
    val uiStatePayments by paymentsViewModel.uiStatePayments.collectAsState()

    LaunchedEffect(Unit, uiStateUser.isAuthenticated) {
        if(uiStateUser.isAuthenticated){
            walletViewModel.getWalletData()
            paymentsViewModel.getPayments(userViewModel)
        }
    }

    AppWindow(
        title = stringResource(R.string.money),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .widthIn(max = maxMoneyWidth),
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
                    text = if (showBalance) formatCurrencyWhole(uiStateWallet.balance) else "****",
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    modifier = Modifier.padding(end = 4.dp),
                    color = Color.Black
                )
                if (showBalance) {
                    Text(
                        text = getDecimalPart(uiStateWallet.balance),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(top = 2.dp),
                        color = Color.Black
                    )
                }
            }
            IconButton(onClick = { showBalance = !showBalance }) {
                Icon(
                    painter = if (showBalance) eyeIconPainter() else eyeOffIconPainter(),
                    contentDescription = if (showBalance) "Ocultar saldo" else "Mostrar saldo",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
            }
        }
    }
    AppWindow(
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(375.dp, 500.dp),
        title = stringResource(R.string.expenses)
    ) {
        Row(
            modifier = Modifier.padding(2.dp,20.dp,0.dp,16.dp)
        ) {
            Text(
                text = stringResource(R.string.this_month),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color.Gray
            )
            Text(
                text = if(uiStatePayments.expensesByMonth.isNotEmpty()) " ${formatCurrencyWhole(uiStatePayments.expensesByMonth[actualMonth].amount)}" else "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Red
            )
        }
        if(vertical) Spacer(Modifier.height(25.dp))
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Row(
                modifier = Modifier.widthIn(max = 430.dp),
                horizontalArrangement = Arrangement.Center,
            ){
                if(uiStatePayments.expensesByMonth.isNotEmpty())
                    BarChart(uiStatePayments.expensesByMonth.map {(month, qty) -> BarChartData(month, qty.toFloat()) }, maxBarHeight)
                else
                    Text(stringResource(R.string.no_cards_associated))
            }
        }
    }
}

var actualMonth = 11

val months = arrayOf(
    R.string.jan,
    R.string.feb,
    R.string.mar,
    R.string.apr,
    R.string.may,
    R.string.jun,
    R.string.jul,
    R.string.aug,
    R.string.sep,
    R.string.oct,
    R.string.nov,
    R.string.dec
)

