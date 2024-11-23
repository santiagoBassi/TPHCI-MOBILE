package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CreditCard
import com.lyrio.ui.styles.Orange
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun Transfer2() {
    var amount by rememberSaveable (key = "transferAmount") { mutableLongStateOf(0L) }
    var selectedMethod by rememberSaveable (key = "selectedMethod") { mutableIntStateOf(0) }


    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Transfer2ContentH(
                    isTablet = isTablet,
                    recipient = "Ezequiel Testoni",
                    amount = amount,
                    onAmountChange = { amount = it },
                    selectedMethod = selectedMethod,
                ) {
                    PaymentMethodsCarousel(
                        cards,
                        selectedMethod,
                        onCurrentPageChanged = { selectedMethod = it },
                        isTablet
                    )
                }
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
                Transfer2ContentV(
                    isTablet = isTablet,
                    recipient = "Ezequiel Testoni",
                    amount = amount,
                    onAmountChange = { amount = it },
                    selectedMethod = selectedMethod,
                ){
                    PaymentMethodsCarousel(cards, selectedMethod, onCurrentPageChanged = { selectedMethod = it }, isTablet)
                }
            }
        }
    }
}

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

@Composable
fun Transfer2ContentH(
    isTablet: Boolean = false,
    recipient: String = "",
    amount: Long = 0L,
    onAmountChange: (Long) -> Unit = {},
    selectedMethod: Int = 0,
    carousel: @Composable () -> Unit = {},
) {
    AppWindow(
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight(if(isTablet) 0.8f else 1f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(if(isTablet) 0.7f else 0.8f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.how_much_to_transfer),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(if(isTablet) 50.dp else 30.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "$$amount",
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                            color = Orange
                        )
                        AppInput(
                            value = if (amount == 0L) "" else amount.toString(),
                            onValueChange = { onAmountChange(it.toLongOrNull() ?: 0L) },
                            label = stringResource(R.string.amount),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        )
                        Text(
                            stringResource(R.string.to) + " $recipient   $selectedMethod",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                    }
                }
                Divider(color = Orange, modifier = Modifier.fillMaxHeight().width(1.dp).padding(vertical = 15.dp))
                Column(
                    modifier = Modifier.weight(1f).fillMaxHeight(if(isTablet) 0.7f else 1f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        stringResource(R.string.payment_method),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.padding(top = if(isTablet) 0.dp else 30.dp)
                    )
                    Spacer(modifier = Modifier.height(if(isTablet) 50.dp else 15.dp))
                    carousel()
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppButton(
                    text = stringResource(R.string.transfer),
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(0.35f)
                )
            }
        }
    }
}

@Composable
fun Transfer2ContentV(
    isTablet: Boolean = false,
    recipient: String = "",
    amount: Long = 0L,
    onAmountChange: (Long) -> Unit = {},
    selectedMethod: Int = 0,
    carousel: @Composable () -> Unit = {},
) {
    AppWindow (
        modifier = Modifier
            .padding(vertical = 8.dp).fillMaxHeight(if(isTablet) 0.7f else 1f)
            .widthIn(max = 520.dp)
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(stringResource(R.string.how_much_to_transfer), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Orange)
                AppInput(
                    value = if(amount == 0L) "" else amount.toString(),
                    onValueChange = { onAmountChange(it.toLongOrNull() ?: 0L) },
                    label = stringResource(R.string.amount),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )
                Text("a $recipient   $selectedMethod", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray,
                    modifier = Modifier.padding(top = 10.dp))
            }
            carousel()
            AppButton(text = stringResource(R.string.transfer), onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(if(isTablet) 0.6f else 0.75f))
        }
    }
}
@Composable
fun PaymentMethodsCarousel(cards: List<CreditCardData>, initialPage: Int = 0, onCurrentPageChanged: (Int) -> Unit = {}, isTablet: Boolean = false) {
    val pagerState = rememberPagerState(initialPage = initialPage ){ cards.size + 1 }
    val coroutineScope = rememberCoroutineScope()

    Column (
        modifier = Modifier.fillMaxWidth(if(isTablet) 0.8f else 1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
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
                    clickEnabled = false,
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
) {
    Card(
        modifier = Modifier
            .clickable(enabled = false){}
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
                Text(stringResource(R.string.money_in_account), style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Spacer(modifier = Modifier.height(12.dp))
                Text(stringResource(R.string.balance) + ": $${"%.2f".format(balance)}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = Color.Black)
            }
        }
    }
}