package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CircularIconButton
import com.lyrio.ui.components.TransferItem
import com.lyrio.ui.components.eyeIconPainter
import com.lyrio.ui.components.eyeOffIconPainter
import com.lyrio.ui.data.viewmodels.PaymentsViewModel
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.styles.LightGray
import com.lyrio.utils.formatCurrencyWhole
import com.lyrio.utils.getDecimalPart

@Composable
fun transferIconPainter(): Painter = painterResource(id = R.drawable.transfer)

@Composable
fun receiveIconPainter(): Painter = painterResource(id = R.drawable.receive)

@Composable
fun cvuAliasIconPainter(): Painter = painterResource(id = R.drawable.cvu_alias)


@Composable
fun Home(
    navigateTransfer1 : () -> Unit = {},
    navigateReceiveMoney : () -> Unit = {},
    navigateProfile : () -> Unit = {},
    navigateMovements : () -> Unit = {},
    navigateMoney : () -> Unit = {},
    viewModelWallet : WalletViewModel,
    viewModelPayments : PaymentsViewModel,
    viewModelUser: UserViewModel
) {
    val configuration = LocalConfiguration.current
    val maxHeight = configuration.screenHeightDp.dp
    val maxWidth = configuration.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 1f)
                        .fillMaxHeight(),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    HomeContent(
                        navigateTransfer1,
                        navigateReceiveMoney,
                        navigateProfile,
                        navigateMovements,
                        navigateMoney,
                        viewModelWallet,
                        viewModelPayments,
                        viewModelUser
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
                        .fillMaxWidth(if (isTablet) 0.7f else 1f)
                        .fillMaxHeight(if (isTablet) 0.75f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HomeContent(
                        navigateTransfer1,
                        navigateReceiveMoney,
                        navigateProfile,
                        navigateMovements,
                        navigateMoney,
                        viewModelWallet,
                        viewModelPayments,
                        viewModelUser
                    )
                }
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
    navigateMoney: () -> Unit = {},
    viewModelWallet: WalletViewModel,
    viewModelPayments : PaymentsViewModel,
    viewModelUser: UserViewModel
) {
    val walletState by viewModelWallet.uiStateWallet.collectAsState()
    val paymentsState by viewModelPayments.uiStatePayments.collectAsState()
    val userState by viewModelUser.uiStateUser.collectAsState()

    LaunchedEffect(Unit, userState.isAuthenticated) {
        if (userState.isAuthenticated) {
            viewModelWallet.getBalance()
            viewModelWallet.getWalletData()
            viewModelPayments.getLastPayments()
            viewModelUser.getCurrentUser()
        }
    }

    var showBalance by remember { mutableStateOf(true) } // Estado para mostrar/ocultar

    AppWindow(
        title = stringResource(id = R.string.money),
        showChevron = true,
        onChevronClick = { navigateMoney() },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .widthIn(max = 450.dp)
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
                    text = if (showBalance) formatCurrencyWhole(walletState.balance) else "****",
                    fontWeight = FontWeight.Bold,
                    fontSize = 38.sp,
                    modifier = Modifier.padding(end = 4.dp),
                    color = Color.Black
                )
                if (showBalance) {
                    Text(
                        text = getDecimalPart(walletState.balance),
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
            if (paymentsState.lastTransfers.isEmpty()) {
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
                    items(paymentsState.lastTransfers) { transfer ->
                        TransferItem(
                            transactionType = if(transfer.payerEmail == userState.email) "Enviaste" else "Recibiste",
                            amount = transfer.amount,
                            recipient = if(transfer.payerEmail == userState.email) transfer.receiverName else transfer.payerName,
                            date = transfer.createdAt.toString()
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
