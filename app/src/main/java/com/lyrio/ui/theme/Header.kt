package com.lyrio.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R

@Composable
fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(10.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Botón del menú (izquierda)
        IconButton(onClick = { /* Acción del menú */ }) {
            Icon(
                Icons.Filled.Menu,
                contentDescription = "Menu Icon",
                tint = MaterialTheme.colorScheme.background
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

        ) {

            Icon(
                painter = painterResource(id = R.drawable.logo), // Reemplaza con tu recurso de ícono
                contentDescription = "Logo Icon",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.size(50.dp)
            )
            Text(
                text = "Lyrio",
                style = MaterialTheme.typography.titleMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 30.sp
                ),
                modifier = Modifier
                    .padding(start = 8.dp)

            )
        }


    }
}
