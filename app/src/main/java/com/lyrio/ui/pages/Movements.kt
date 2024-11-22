package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.TransferItem
import com.lyrio.ui.styles.OffWhite
import java.util.Date

@Composable
fun Movements() {

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp,20.dp,100.dp,15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovementsContent()
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovementsContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementsContent(){
    var searchText by remember { mutableStateOf("") }
    val recentTransfers = remember {
        mutableStateListOf(
            TransferData(transactionType = R.string.sent, amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = R.string.received, amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = R.string.received, amount = 10000.0, recipient = "María García", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = R.string.received, amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = R.string.received, amount = 10000.0, recipient = "María García", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = R.string.received, amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = R.string.sent, amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = R.string.received, amount = 10000.0, recipient = "María García", date = Date())
        )
    }

    AppWindow(
        title = stringResource(R.string.movements),
    ) {
        Column(modifier = Modifier.padding(horizontal = 10.dp)) {
            SearchBar(
                query = searchText,
                onQueryChange = { searchText = it },
                onSearch = { },
                active = false,
                onActiveChange = { },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                },
                trailingIcon = {
                    IconButton(
                        onClick = { searchText = "" },
                    ) {
                        if (searchText.isNotEmpty()) {
                            Icon(
                                painter = painterResource(id = R.drawable.clear),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                placeholder = { Text(stringResource(R.string.search), color = Color.Gray) },
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                colors = SearchBarDefaults.colors(OffWhite),
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(recentTransfers.filter { transfer ->
                    transfer.recipient.contains(searchText, ignoreCase = true) ||
                            transfer.amount.toString()
                                .contains(searchText, ignoreCase = true) ||
                            transfer.date.toString().contains(searchText, ignoreCase = true)
                }) { transfer ->
                    TransferItem(
                        transactionType = stringResource(transfer.transactionType),
                        amount = transfer.amount,
                        recipient = transfer.recipient,
                        date = transfer.date
                    )
                }
            }
        }
    }
}

