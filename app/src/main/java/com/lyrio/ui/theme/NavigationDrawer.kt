package com.lyrio.ui.theme

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.coroutines.launch

data class NavItem(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val selected: Boolean,
    val onClick: () -> Unit
)

val items = listOf(
    NavItem(Icons.Filled.Home, "Transferir", "Transfer Icon", false) { /* Acción Transferir */ },
    NavItem(Icons.Filled.Home, "Mi dinero", "Transfer Icon", false) { /* Acción Mi dinero */ },
    NavItem(Icons.Filled.Home, "Movimientos", "Transfer Icon", false) { /* Acción Movimientos */ },
    NavItem(Icons.Filled.Home, "Inversiones", "Transfer Icon", false) { /* Acción Inversiones */ },
    NavItem(Icons.Filled.Home, "Cobrar con link de pago", "Transfer Icon", false) { /* Acción Cobrar con link */ },
    NavItem(Icons.Filled.Home, "Mis tarjetas", "Transfer Icon", false) { /* Acción Mis tarjetas */ },
)

@Composable
fun NavigationDrawer(content: @Composable () -> Unit){
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.End
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "close icon",
                        modifier = Modifier.size(35.dp),
                        tint = Color.Black
                    )
                }
                items.forEach { item ->
                    NavigationDrawerItem(
                        label = {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(

                                ){
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.title,
                                        modifier = Modifier.size(24.dp),
                                        tint = Color.Black
                                    )
                                    Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = item.title,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = Color.Black,
                                    ),)
                                }

                                Icon(
                                    imageVector = Icons.Filled.PlayArrow, // Ícono de flecha
                                    contentDescription = "Arrow",
                                    modifier = Modifier.size(24.dp),
                                    tint = Color.Black
                                )
                            }
                        },
                        selected = item.selected,
                        onClick = item.onClick
                        )
                    HorizontalDivider()
                }


            }
        }
    ) {

        DefaultLayout(
            onButtonClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            },
            content = content
        )
    }
}



