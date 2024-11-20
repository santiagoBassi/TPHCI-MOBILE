package com.lyrio.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.lyrio.R
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun Transfer2() {
    val cards = listOf(
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
    )
    var selectedMethod by remember { mutableIntStateOf(0) }
    var amount by remember { mutableLongStateOf(0) }
    val recipient = "Ezequiel Testoni"

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AppWindow {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("¿Cuánto querés transferir?", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
                    AppInput(
                        value = if(amount.toInt() == 0) "" else amount.toString(),
                        onValueChange = { amount = it.toLongOrNull() ?: 0 },
                        label = "Monto",
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    )
                    Text("a $recipient", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray,
                        modifier = Modifier.padding(top = 10.dp))
                }
                PaymentMethodsCarousel(cards, onCurrentPageChanged = { selectedMethod = it })
                AppButton(text = "Transferir", onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(0.75f))
            }
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PaymentMethodsCarousel(cards: List<CreditCardData>, onCurrentPageChanged: (Int) -> Unit = {}) {
    val pagerState = rememberPagerState{ cards.size + 1 }
    val coroutineScope = rememberCoroutineScope()

    Column {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 10.dp),
            pageSpacing = 8.dp
        ) { page ->
            if (page == 0) {
                AccountBalanceOption()
            } else {
                CreditCard(
                    cardNumber = cards[page - 1].cardNumber,
                    logo = cards[page - 1].logo,
                    primaryColor = cards[page - 1].primaryColor,
                    secondaryColor = cards[page - 1].secondaryColor,
                    logoSize = cards[page - 1].logoSize,
                )
            }
        }

        HorizontalPagerIndicator(
            pageCount = cards.size + 1, // +1 para la opción de "Dinero en cuenta"
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 10.dp, bottom = 10.dp)
                .clickable {
                    val currentPage = pagerState.currentPage
                    val totalPages = cards.size + 1 // +1 para la opción de "Dinero en cuenta"
                    val nextPage = if (currentPage < totalPages - 1) currentPage + 1 else 0
                    coroutineScope.launch { pagerState.animateScrollToPage(nextPage) }
                }
        )
    }
    LaunchedEffect(key1 = pagerState.currentPage) {
        onCurrentPageChanged(pagerState.currentPage)
    }
}

@Composable
fun AccountBalanceOption(
    balance: Double = 100000.0,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .clickable { onClick() }
            .fillMaxWidth()
            .height(180.dp)
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFFC98A0C), Color(0xFFDAA201))
                    )
                )
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Dinero en cuenta", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(12.dp))
                Text("Saldo: $${"%.2f".format(balance)}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}