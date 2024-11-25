package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.data.DataSourceException
import com.lyrio.data.model.Card
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CreditCard
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.styles.Red


@Composable
fun CreditCards(
    navigateAddCreditCard: () -> Unit,
    walletViewModel: WalletViewModel,
    userViewModel: UserViewModel
) {

    val walletUiState by walletViewModel.uiStateWallet.collectAsState()
    val uiStateUser by userViewModel.uiStateUser.collectAsState()

    LaunchedEffect(Unit, uiStateUser.isAuthenticated) {
        if(uiStateUser.isAuthenticated)
            walletViewModel.getCards()
    }

    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize()
                .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 0.65f)
                        .fillMaxHeight(if (isTablet) 0.7f else 0.85f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreditCardsContent(
                        cards = walletUiState.cards,
                        navigateAddCreditCard = navigateAddCreditCard,
                        isLandscape = true,
                        walletViewModel = walletViewModel
                    )
                }
            }
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxHeight().verticalScroll(rememberScrollState())
                        .fillMaxWidth(if (isTablet) 0.8f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CreditCardsContent(
                        cards = walletUiState.cards,
                        navigateAddCreditCard = navigateAddCreditCard,
                        walletViewModel = walletViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun CreditCardsContent(
    cards: List<Card>,
    navigateAddCreditCard: () -> Unit,
    isLandscape: Boolean = false,
    walletViewModel: WalletViewModel
) {
    var openAlertDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableIntStateOf(0) }
    val uiStateWallet by walletViewModel.uiStateWallet.collectAsState()

    when {
        openAlertDialog -> {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    walletViewModel.clearError()
                    walletViewModel.deleteCard(cardToDelete)
                    openAlertDialog = false
                },
                dialogTitle = stringResource(R.string.remove_card),
                dialogText = stringResource(R.string.card_model_lore),
                dismissText = stringResource(R.string.cancel),
                confirmText = stringResource(R.string.remove)
            )
        }
    }

    AppWindow(
        title = stringResource(R.string.credit_cards),
        modifier = Modifier.defaultMinSize(minHeight = 300.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (cards.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 12.dp)
                ) {
                    for (card in cards) {
                        val logo = getCardType(card.number)
                        val primary =
                            when (logo) {
                                R.drawable.visa -> Color(0xFF120269)
                                R.drawable.amex -> Color(0xFFC98A0C)
                                else -> Color(0xFF000000)
                            }
                        val secondary =
                            when (logo) {
                                R.drawable.visa -> Color(0xFF2204C6)
                                R.drawable.amex -> Color(0xFFDAA201)
                                else -> Color(0xFF5f5f5f)
                            }
                        CardRow(card = CreditCardData(
                            cardNumber = card.number,
                            logo = if ( logo == R.drawable.visa) R.drawable.visa_white else logo,
                            primaryColor = primary,
                            secondaryColor = secondary,
                            logoSize = 80.dp
                        ), onDelete = {
                            cardToDelete = card.id
                            openAlertDialog = true
                        })
                    }
                }
            } else {
                Box(
                    modifier = Modifier
                        .padding(vertical = 50.dp)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = stringResource(R.string.no_cards_associated),
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                    )
                }
            }
            if(uiStateWallet.error != null) {
                val errorCode = uiStateWallet.error?.code ?: DataSourceException.UNEXPECTED_ERROR_CODE
                Text(
                    text = stringResource(errorMap[errorCode]!!),
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            AppButton(text = stringResource(R.string.add_card), width = if(isLandscape) 0.5f else 0.8f,
                onClick = navigateAddCreditCard, modifier = Modifier.padding(vertical = 15.dp))
        }
    }
}

@Composable
fun CardRow(card: CreditCardData, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .widthIn(max = 600.dp)
            .padding(vertical = 2.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CreditCard(
            cardNumber = card.cardNumber,
            logo = card.logo,
            primaryColor = card.primaryColor,
            secondaryColor = card.secondaryColor,
            logoSize = card.logoSize,
            modifier = Modifier.weight(1f),
            clickEnabled = false
        )
        IconButton(onClick = onDelete) {
            Icon(painter = painterResource(R.drawable.delete), contentDescription = "Borrar",
                tint = Red, modifier = Modifier.size(24.dp))
        }
    }
}

class CreditCardData(
    val cardNumber: String,
    val logo: Int,
    val primaryColor: Color,
    val secondaryColor: Color,
    val logoSize: Dp,
)

private val errorMap = mapOf(
    DataSourceException.DATA_ERROR to R.string.invalid_data,
    DataSourceException.UNAUTHORIZED_ERROR_CODE to R.string.unauthorized,
    DataSourceException.NOT_FOUND_ERROR_CODE to R.string.card_not_found,
    DataSourceException.INTERNAL_SERVER_ERROR_CODE to R.string.internal_server_error,
    DataSourceException.CONNECTION_ERROR_CODE to R.string.connection_error,
    DataSourceException.UNEXPECTED_ERROR_CODE to R.string.unexpected_error
)


