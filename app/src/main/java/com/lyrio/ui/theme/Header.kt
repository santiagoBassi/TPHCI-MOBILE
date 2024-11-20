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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R

@Composable
fun Header(onButtonClick: () -> Unit, state: DrawerState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(
                MaterialTheme.colorScheme.secondary
            )
            .windowInsetsPadding(WindowInsets.statusBars),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center

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


    }
}
