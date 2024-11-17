package com.lyrio.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyrio.R

@Composable
fun BottomBar(){
    BottomAppBar(
        modifier = Modifier.shadow(
            elevation = 15.dp,
        ),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.secondary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { /* Acción del primer icono */ }) {
                Icon(Icons.Filled.Home, contentDescription = "Home Icon", modifier = Modifier.size(35.dp))
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
                    painter = painterResource(id = R.drawable.qr_code_2_24dp_e8eaed_fill0_wght400_grad0_opsz24), // Reemplaza "ic_flower" con tu recurso
                    contentDescription = "Qr code icon",
                    modifier = Modifier.size(40.dp)

                )
            }
            IconButton(onClick = { /* Acción del tercer icono */ }) {
                Icon(Icons.Filled.AccountCircle, contentDescription = "Qr code Icon", modifier = Modifier.size(35.dp))
            }
        }
    }
}