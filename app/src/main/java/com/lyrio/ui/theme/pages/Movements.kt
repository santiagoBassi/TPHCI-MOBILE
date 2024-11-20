package com.lyrio.ui.theme.pages

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
import androidx.compose.ui.res.painterResource // Import para painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.theme.components.AppWindow
import com.lyrio.ui.theme.components.TransferItem
import com.lyrio.ui.theme.styles.LyrioTheme
import com.lyrio.ui.theme.styles.OffWhite
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun Movements() {
    var searchText by remember { mutableStateOf("") }
    val recentTransfers = remember {
        mutableStateListOf(
            TransferData(transactionType = "Enviaste", amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 10000.0, recipient = "María García", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 10000.0, recipient = "María García", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 1500.0, recipient = "Supermercado", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 5000.0, recipient = "Juan Pérez", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 1000.0, recipient = "Celular", date = Date()),
            TransferData(transactionType = "Enviaste", amount = 200.0, recipient = "Kiosco", date = Date()),
            TransferData(transactionType = "Recibiste", amount = 10000.0, recipient = "María García", date = Date())
        )
    }
    LyrioTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AppWindow(
                title = "Movimientos",
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
                        placeholder = { Text("Buscar", color = Color.Gray) },
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
                                transactionType = transfer.transactionType,
                                amount = transfer.amount,
                                recipient = transfer.recipient,
                                date = transfer.date
                            )
                        }
                    }
                }
            }
        }
    }
}


