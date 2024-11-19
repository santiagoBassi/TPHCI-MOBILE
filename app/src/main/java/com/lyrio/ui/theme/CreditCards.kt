package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.lyrio.R

@Preview(showBackground = true)
@Composable
fun CreditCards() {
    val cards = remember { mutableStateListOf(
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
        )
    ) }

    LyrioTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(
                title = "Tarjetas",
                modifier = Modifier.fillMaxWidth(0.95f).fillMaxHeight(0.95f),
                ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (cards.isNotEmpty()) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            items(cards) { card ->
                                CardRow(card = card, onDelete = { cards.remove(card) })
                            }
                        }
                    } else {
                        Text(
                            text = "No tenés tarjetas asociadas",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                        )
                    }
                    AppButton(text = "Agregar tarjeta", width = 0.8f,onClick = {})
                }
            }
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
            modifier = Modifier.weight(1f)
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
