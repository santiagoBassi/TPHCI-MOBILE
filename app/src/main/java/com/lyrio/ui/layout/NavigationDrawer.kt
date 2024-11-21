package com.lyrio.ui.layout

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import com.lyrio.R
import com.lyrio.ui.components.AlertDialog

data class NavItem(
    val icon: Int,
    val title: String,
    val description: String,
    val selected: Boolean,
    val onClick: () -> Unit
)

val items = listOf(
    NavItem(R.drawable.send_money_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Transferir", "Transfer Icon", false) { /* Acción Transferir */ },
    NavItem(R.drawable.account_balance_wallet_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Dinero", "Transfer Icon", false) { /* Acción Mi dinero */ },
    NavItem(R.drawable.list_alt_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Movimientos", "Transfer Icon", false) { /* Acción Movimientos */ },
    NavItem(R.drawable.currency_exchange_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Inversiones", "Transfer Icon", false) { /* Acción Inversiones */ },
    NavItem(R.drawable.link_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Link de pago", "Transfer Icon", false) { /* Acción Cobrar con link */ },
    NavItem(R.drawable.credit_card_24dp_e8eaed_fill0_wght400_grad0_opsz24, "Tarjetas", "Transfer Icon", false) { /* Acción Mis tarjetas */ },
)

@Composable
fun NavigationDrawer(modifier: Modifier = Modifier, content: @Composable () -> Unit, drawerState: DrawerState) {

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = modifier,
        drawerContent = {
            ModalDrawerSheet {
                NavigationDrawerContent()
            }
        }
    ) {
        content()
    }

}

@Composable
fun NavigationDrawerContent(){
    var openAlertDialog by remember { mutableStateOf(false) }
    when {
        openAlertDialog -> {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    /* TODO */ // Cerrar sesión
                    openAlertDialog = false
                },
                dialogTitle = "Cerrar sesión",
                dialogText = "¿Estás seguro de que querés cerrar sesión?",
                dismissText = "Cancelar",
                confirmText = "Cerrar sesión"
            )
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            HorizontalDivider()
            items.forEach { item ->
                Spacer(modifier = Modifier.height(10.dp))
                NavigationDrawerItem(
                    label = {
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
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                Text(
                                    text = item.title,
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
                    },
                    selected = item.selected,
                    onClick = item.onClick
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 35.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logout),
                contentDescription = "Cerrar sesión",
                modifier = Modifier.size(24.dp),
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Cerrar sesión",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = Color.Black,
                ),
                modifier = Modifier.clickable {
                    openAlertDialog = true
                }
            )
        }
    }
}



