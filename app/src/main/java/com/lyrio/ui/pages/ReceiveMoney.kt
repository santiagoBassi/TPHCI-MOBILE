package com.lyrio.ui.pages

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.WalletViewModel

@Composable
fun ReceiveMoney(
    context: Context,
    navigatePaylink: () -> Unit = {},
    walletViewModel: WalletViewModel
){
    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ReceiveMoneyContentH(
                    isTablet = isTablet,
                    cvuAlias = { CVUAliasWindow(isTablet, context, walletViewModel) },
                    navigatePaylink = navigatePaylink,
                    walletViewModel
                )
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReceiveMoneyContentV(
                    isTablet = isTablet,
                    cvuAlias = { CVUAliasWindow(isTablet, context, walletViewModel) },
                    navigatePaylink = navigatePaylink,
                    walletViewModel = walletViewModel
                )
            }
        }
    }
}

@Composable
fun ReceiveMoneyContentH(
    isTablet: Boolean = false,
    cvuAlias: @Composable () -> Unit,
    navigatePaylink: () -> Unit = {},
    walletViewModel: WalletViewModel
) {
    var showTODOModal by remember { mutableStateOf(false) }

    when {
        showTODOModal -> {
            AlertDialog(
                onDismissRequest = { showTODOModal = false },
                onConfirmation = {
                    showTODOModal = false
                },
                dialogTitle = stringResource(R.string.ups),
                dialogText = stringResource(R.string.not_available_yet),
                dismissText = "OK",
            )
        }
    }

    AppWindow(
        title = stringResource(R.string.receive_money),
        modifier = Modifier
            .fillMaxWidth(if (isTablet) 0.8f else 1f)
            .fillMaxHeight(if (isTablet) 0.65f else 1f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.receive_lore),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp),
                color = Color.Black
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 15.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(0.55f),
                ) {
                    cvuAlias()
                }
                Column(
                    modifier = Modifier.weight(0.45f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AppButton(
                        text = stringResource(R.string.generate_link),
                        onClick = { showTODOModal = true },
                        width = 0.8f
                    )
                }
            }
        }
    }
}

@Composable
fun ReceiveMoneyContentV(
    isTablet: Boolean = false,
    cvuAlias: @Composable () -> Unit,
    navigatePaylink: () -> Unit = {},
    walletViewModel: WalletViewModel
){
    var showTODOModal by remember { mutableStateOf(false) }

    when {
        showTODOModal -> {
            AlertDialog(
                onDismissRequest = { showTODOModal = false },
                onConfirmation = {
                    showTODOModal = false
                },
                dialogTitle = stringResource(R.string.ups),
                dialogText = stringResource(R.string.not_available_yet),
                dismissText = "OK",
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .fillMaxHeight(if (isTablet) 0.6f else 1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppWindow(
            title = stringResource(R.string.receive_money),
            modifier = Modifier.widthIn(max = 550.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = stringResource(R.string.receive_lore1), fontWeight = FontWeight.Medium, color = Color.Black)
                    Text(text = stringResource(R.string.receive_lore2), fontWeight = FontWeight.Medium, color = Color.Black)
                }
                cvuAlias()
                AppButton(
                    text = stringResource(R.string.generate_link),
                    onClick = { showTODOModal = true },
                    width = if(isTablet) 0.6f else 0.8f
                )
            }
        }
    }
}

@Composable
fun CVUAliasWindow(
    isTablet: Boolean = false,
    context: Context,
    walletViewModel: WalletViewModel
){
    val uiStateWallet by walletViewModel.uiStateWallet.collectAsState()

    AppWindow(
        title = stringResource(R.string.cvu_alias),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .padding(top = if (!isTablet) 12.dp else 0.dp)
            .widthIn(max = 450.dp),
        background = Color(0xFFF5F5F5)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp, 12.dp, 12.dp, 0.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.cvu), fontWeight = FontWeight.Bold, color = Color.Black)
                Text(uiStateWallet.cbu, color = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.alias), fontWeight = FontWeight.Bold, color = Color.Black)
                Text(uiStateWallet.alias, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.copy_cvu_alias),
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                IconButton(onClick = {
                    copyCVUandAlias(context, uiStateWallet.cbu, uiStateWallet.alias)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.copy),
                        contentDescription = "Copy",
                        tint = Color.Gray,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@SuppressLint("ServiceCast")
fun copyCVUandAlias(
    context: Context,
    cvu: String,
    alias: String,
) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("CVU and Alias", "CVU: $cvu\nAlias: $alias")
    clipboardManager.setPrimaryClip(clipData)
}