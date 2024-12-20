package com.lyrio.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lyrio.R
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.navigation.Screen


@Composable
fun BottomBar(navController: NavController){
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

    BottomAppBar(
        modifier = Modifier.shadow(
            elevation = 15.dp,
        ),
        containerColor = MaterialTheme.colorScheme.secondary,
        contentColor = MaterialTheme.colorScheme.background
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.navigate(Screen.Home){
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
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.secondary
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

