package com.lyrio.ui.layout

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.lyrio.R
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.navigation.Screen

data class NavItem(
    val icon: Int,
    val title: Int,
    val description: String,
    val selected: Boolean,
    val page: Screen
)

val items = listOf(
    NavItem(R.drawable.send_money_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.transfer, "Transfer Icon", false,Screen.Transfer1),
    NavItem(R.drawable.account_balance_wallet_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.money, "Transfer Icon", false,
        Screen.Money) ,
    NavItem(R.drawable.list_alt_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.movements, "Transfer Icon", false,
        Screen.Movements) ,
    NavItem(R.drawable.currency_exchange_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.invest, "Transfer Icon", false,
        Screen.Invest) ,
    NavItem(R.drawable.link_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.pay_link, "Transfer Icon", false,
        Screen.ReceiveMoney),
    NavItem(R.drawable.credit_card_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.cards, "Transfer Icon", false,
        Screen.CreditCards),
    NavItem(R.drawable.logout_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.logout, "Logout Icon", false,
        Screen.Landing)
)

@Composable
fun NavigationDrawer(navController: NavController, modifier: Modifier = Modifier, content: @Composable () -> Unit, drawerState: DrawerState) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerContent(navController)
            }
        }
    ) {
        content()
    }

}

@Composable
fun NavigationDrawerContent(navController: NavController){
    var openAlertDialog by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    when {
        openAlertDialog -> {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    openAlertDialog = false
                    navController.navigate(Screen.Landing)
                },
                dialogTitle = stringResource(R.string.logout),
                dialogText = stringResource(R.string.logout_text),
                dismissText = stringResource(R.string.cancel),
                confirmText = stringResource(R.string.logout)
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        HorizontalDivider()

        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            items.forEach { item ->
                Spacer(modifier = Modifier.height(10.dp))
                NavigationDrawerItem(
                    label = {
                        NavDrawerItem(item)
                    },
                    selected = item.selected,
                    onClick = { navController.navigate(item.page) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }
        } else {
            items.dropLast(1).forEach { item ->
                Spacer(modifier = Modifier.height(10.dp))
                NavigationDrawerItem(
                    label = {
                        NavDrawerItem(item)
                    },
                    selected = item.selected,
                    onClick = { navController.navigate(item.page) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }

            Spacer(modifier = Modifier.weight(1f))
            items.lastOrNull()?.let { item ->
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                NavigationDrawerItem(
                    label = {
                        NavDrawerItem(item)
                    },
                    selected = item.selected,
                    onClick = { openAlertDialog = true }
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }
        }
    }
}


@Composable
fun NavDrawerItem(item: NavItem){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = stringResource(item.title),
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = stringResource(item.title),
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                )
            )
        }
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Chevron",
            modifier = Modifier.size(24.dp),
            tint = Color.Black
        )
    }
}

