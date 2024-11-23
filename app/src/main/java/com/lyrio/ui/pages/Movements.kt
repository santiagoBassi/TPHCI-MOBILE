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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.TransferItem
import com.lyrio.ui.data.viewmodels.PaymentsViewModel
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.styles.OffWhite
import java.util.Date

@Composable
fun Movements(
    viewModelPayments : PaymentsViewModel,
    viewModelUser: UserViewModel
) {

    var maxHeight = 1000.dp

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
                MovementsContent( viewModelPayments = viewModelPayments,
                    viewModelUser = viewModelUser)
            }
        }

        else -> {
            Column(
                modifier = Modifier
                    .fillMaxSize().onSizeChanged { size -> if(size.height.dp > maxHeight) maxHeight = size.height.dp }
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MovementsContent(
                    viewModelPayments = viewModelPayments,
                    viewModelUser = viewModelUser,
                    maxHeight = maxHeight)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovementsContent(
    viewModelPayments : PaymentsViewModel,
    viewModelUser: UserViewModel,
    maxHeight: Dp = 1000.dp
){
    var searchText by remember { mutableStateOf("") }

    val paymentsState by viewModelPayments.uiStatePayments.collectAsState()
    val userState by viewModelUser.uiStateUser.collectAsState()

    LaunchedEffect(Unit, userState.isAuthenticated) {
        if (userState.isAuthenticated) {
            viewModelPayments.getPayments()
            viewModelUser.getCurrentUser()
        }
    }

    AppWindow(
        title = stringResource(R.string.movements),
        modifier = Modifier.widthIn(max = 400.dp)
            .height(maxHeight)
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
                colors = SearchBarDefaults.colors(OffWhite,
                        contentColorFor(
                            backgroundColor = Color.Black
                        )
                    ),
            ) {

            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(paymentsState.lastTransfers) { transfer ->
                    TransferItem(
                        transactionType = if(transfer.payerEmail == userState.email) "Enviaste" else "Recibiste",
                        amount = transfer.amount,
                        recipient = if(transfer.payerEmail == userState.email) transfer.receiverName else transfer.payerName,
                        date = transfer.createdAt
                    )
                }
            }
        }
    }


}
