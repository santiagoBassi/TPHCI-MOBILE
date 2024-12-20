package com.lyrio.ui.layout

import android.content.res.Configuration
import android.transition.Scene
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.NavigationRail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.lyrio.R
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.lyrio.LyrioApp
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.data.viewmodels.ViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.navigation.Screen


@Composable
fun isMobile(): Boolean {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp < 1000
    } else {
        configuration.screenWidthDp < 600
    }
}



val itemsNavBar = listOf(
    NavItem(R.drawable.home_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.home, "Home Icon", false,
        Screen.Home),
    NavItem(R.drawable.account_circle_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.profile, "Profile Icon", false,
        Screen.Profile),
    NavItem(R.drawable.qr_code_2_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.qr, "QR Icon", false, Screen.Home) ,
    NavItem(R.drawable.transfer, R.string.transfer, "Transfer Icon", false,
        Screen.Transfer1) ,
    NavItem(R.drawable.account_balance_wallet_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.money, "Money Icon", false, Screen.Money),
    NavItem(R.drawable.list_alt_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.movements, "Movements Icon", false, Screen.Movements) ,
    NavItem(R.drawable.currency_exchange_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.investments, "Invest Icon", false, Screen.Invest) ,
    NavItem(R.drawable.credit_card_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.cards, "Card Icon", false,
        Screen.CreditCards) ,
    NavItem(R.drawable.logout_24dp_e8eaed_fill0_wght400_grad0_opsz24, R.string.logout, "Logout Icon", false,
        Screen.Home),
    )


@Composable
fun LeftBarMobile(onButtonClick: () -> Unit, navController: NavController, state: DrawerState, windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass) {
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

    NavigationRail(
        modifier = Modifier
            .shadow(15.dp)
            .zIndex(1f),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            IconButton(
                onClick = onButtonClick,
                modifier = Modifier.padding(8.dp, 0.dp)) {
                Icon(
                    imageVector = if (state.isOpen) Icons.Filled.Close else Icons.Filled.Menu,
                    contentDescription = "Menu Icon",
                    modifier = Modifier
                        .size(35.dp)

                )
            }
            IconButton(onClick = {navController.navigate(Screen.Home){
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            } }) {

                Icon(
                    painter = painterResource(id = R.drawable.home_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                    contentDescription = "Home",
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = { showTODOModal = true },
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(50)
                    )
                    .size(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.qr_code_2_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                    contentDescription = "Qr code icon",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = { navController.navigate(Screen.Profile) }) {
                Icon(
                    painter = painterResource(id = R.drawable.account_circle_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                    contentDescription = "Account Circle",
                    modifier = Modifier.size(35.dp)
                )
            }

        }
    }
}

@Composable
fun LeftBarTablet(
    navController: NavController,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass,
    viewModel: ViewModel
) {
    val uiState by viewModel.uiState.collectAsState()

    NavigationRail(
        modifier = Modifier
            .shadow(15.dp)
            .zIndex(1f),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.background,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp, 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo Icon",
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = "Lyrio",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 30.sp
                    ),
                    modifier = Modifier
                        .padding(8.dp,0.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 20.dp),
                horizontalAlignment = if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM) Alignment.CenterHorizontally else Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                itemsNavBar.dropLast(1).forEach { item ->
                    RenderRailItem(item = item, selectedItem =  uiState.selectedItem) {
                        viewModel.setSelectedItem(item.icon)
                        navController.navigate(item.page)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                itemsNavBar.lastOrNull()?.let { item ->
                    RenderRailItem(item = item, selectedItem = uiState.selectedItem) {
                        viewModel.setSelectedItem(item.icon)
                        navController.navigate(item.page)
                    }
                }
            }
        }
    }
}

@Composable
fun RenderRailItem(item: NavItem, selectedItem: Int, onItemSelected: () -> Unit){
    var windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    NavigationRailItem(
        icon = {
            if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM)
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ItemContent(item,selectedItem)
                }
            else
                Row (
                    modifier = Modifier.fillMaxHeight(0.30f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    
                ) {
                    ItemContent(item,selectedItem)
                }
        },
        selected = (selectedItem == item.icon),
        onClick = onItemSelected,
        modifier =
            if(windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM)
                Modifier.padding(0.dp, 10.dp)
            else
                Modifier.padding(0.dp, 1.dp)
        ,
        colors = NavigationRailItemColors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.primary,
            unselectedIconColor = MaterialTheme.colorScheme.background,
            unselectedTextColor = MaterialTheme.colorScheme.background,
            selectedIndicatorColor = Color.Transparent,
            disabledIconColor = Color.Transparent,
            disabledTextColor = Color.Transparent
        )
    )
}

@Composable
fun ItemContent(item: NavItem, selectedItem: Int){
    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
    Icon(
        painter = painterResource(id = item.icon),
        contentDescription = stringResource(item.title),
        modifier = Modifier.size(30.dp),
        tint = if (selectedItem == item.icon) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.background
        },
    )
    if (windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.EXPANDED)
        Spacer(
            modifier = Modifier.size(15.dp)
        )
    Text(
        text = stringResource(item.title),
        style = MaterialTheme.typography.titleMedium.copy(
            color = if (selectedItem == item.icon) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.background
            },
        )
    )
}