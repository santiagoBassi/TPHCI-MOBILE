package com.lyrio.ui.theme

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
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
import androidx.compose.material3.NavigationRailItemColors
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.window.layout.WindowMetricsCalculator
import kotlinx.coroutines.launch

@Composable
fun isMobil(): Boolean {
    val configuration = LocalConfiguration.current
    return if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        configuration.screenWidthDp < 1000
    } else {
        configuration.screenWidthDp < 600
    }
}



val itemsNavBar = listOf(
    NavItem(R.drawable.home_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Home", "Home Icon", false) { /* Acción Transferir */ },
    NavItem(R.drawable.account_circle_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Perfil", "Profile Icon", false) { /* Acción Transferir */ },
    NavItem(R.drawable.qr_code_2_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Pago QR", "QR Icon", false) { /* Acción Transferir */ },
    NavItem(R.drawable.send_money_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Transferir", "Transfer Icon", false) { /* Acción Transferir */ },
    NavItem(R.drawable.account_balance_wallet_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Dinero", "Money Icon", false) { /* Acción Mi dinero */ },
    NavItem(R.drawable.list_alt_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Movimientos", "Movements Icon", false) { /* Acción Movimientos */ },
    NavItem(R.drawable.currency_exchange_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Inversiones", "Invest Icon", false) { /* Acción Inversiones */ },
    NavItem(R.drawable.link_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Link de pago", "Link Icon", false) { /* Acción Cobrar con link */ },
    NavItem(R.drawable.credit_card_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Tarjetas", "Card Icon", false) { /* Acción Mis tarjetas */ },
    NavItem(R.drawable.logout_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Cerrar sesion", "Logout Icon", false) { /* Acción Transferir */ },
    )



@Composable
fun LeftBarMobil(onButtonClick: () -> Unit, state: DrawerState, windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass) {
    var selectedItem by remember { mutableIntStateOf(0) }

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
            IconButton(onClick = { /* Acción del primer icono */ }) {

                Icon(
                    painter = painterResource(id = R.drawable.home_24dp_e8eaed_fill0_wght400_grad0_opsz24),
                    contentDescription = "Home",
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                onClick = { /* Acción del segundo icono */ },
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
            IconButton(onClick = { /* Acción del tercer icono */ }) {
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
fun LeftBarTablet( windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass) {
    var selectedItem by remember { mutableIntStateOf(0) }

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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                itemsNavBar.dropLast(1).forEach { item ->
                    RenderRailItem(item = item, selectedItem =  selectedItem) { selectedItem = item.icon }
                }
                Spacer(modifier = Modifier.weight(1f))
                itemsNavBar.lastOrNull()?.let { item ->
                    RenderRailItem(item = item, selectedItem = selectedItem) { selectedItem = item.icon }
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
                    itemContent(item,selectedItem)
                }
            else
                Row (
                    modifier = Modifier.fillMaxHeight(0.25f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    itemContent(item,selectedItem)
                }
        },
        selected = selectedItem == item.icon,
        onClick = onItemSelected,
        modifier = Modifier.padding(0.dp, 10.dp),
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
fun itemContent(item: NavItem, selectedItem: Int){
    Icon(
        painter = painterResource(id = item.icon),
        contentDescription = item.title,
        modifier = Modifier.size(30.dp)
    )
    Text(
        text = item.title,
        style = MaterialTheme.typography.titleMedium.copy(
            color = if (selectedItem == item.icon) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.background
            }
        )
    )
}