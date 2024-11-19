package com.lyrio.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp

@Composable
fun LeftBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    NavigationRail(
        modifier = Modifier
            .padding(0.dp, 100.dp,0.dp,0.dp)
            .shadow(15.dp),
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.secondary,



    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
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