package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AlertDialog
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CreditCard
import com.lyrio.ui.styles.Red

@Preview(showBackground = true)
@Composable
fun CreditCards(
    navigateAddCreditCard: () -> Unit = {}
) {
    val creditCards = remember { mutableStateListOf(
        CreditCardData(
            cardNumber = "4734 5678 9012 3456",
            logo = R.drawable.visa_white,
            primaryColor = Color(0xFF120269),
            secondaryColor = Color(0xFF2204C6),
            logoSize = 80.dp
        ),
        CreditCardData(
            cardNumber = "2345 5678 9012 3456",
            logo = R.drawable.mastercard,
            primaryColor = Color(0xFF000000),
            secondaryColor = Color(0xFF5f5f5f),
            logoSize = 80.dp
        ),
    )}

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize().verticalScroll(rememberScrollState())
                    .padding(120.dp,20.dp,170.dp,15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreditCardsContent(creditCards, onCardDelete = { creditCards.remove(it)}, navigateAddCreditCard)
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize().verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CreditCardsContent(creditCards, onCardDelete = { creditCards.remove(it)}, navigateAddCreditCard)
            }
        }
    }
}

@Composable
fun CreditCardsContent(cards: List<CreditCardData>, onCardDelete: (CreditCardData) -> Unit, navigateAddCreditCard: () -> Unit) {
    var openAlertDialog by remember { mutableStateOf(false) }
    var cardToDelete by remember { mutableStateOf<CreditCardData?>(null) }

    when {
        openAlertDialog -> {
            AlertDialog(
                onDismissRequest = { openAlertDialog = false },
                onConfirmation = {
                    cardToDelete?.let(onCardDelete)
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
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (cards.isNotEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth().padding(vertical = 12.dp)
                ) {
                    for (card in cards) {
                        CardRow(card = card, onDelete = {
                            cardToDelete = card
                            openAlertDialog = true })
                    }
                }
            } else {
                Text(
                    text = stringResource(R.string.no_cards_associated),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                )
            }
            AppButton(text = stringResource(R.string.add_card), width = 0.8f,onClick = navigateAddCreditCard, modifier = Modifier.padding(vertical = 15.dp))
        }
    }
}

@Composable
fun CardRow(card: CreditCardData, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
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

