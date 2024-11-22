package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.components.CardFace
import com.lyrio.ui.components.FlippableCard
import com.lyrio.ui.components.NewCreditCardBack
import com.lyrio.ui.components.NewCreditCardFront
import com.lyrio.ui.components.RotationAxis
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCreditCard(
    navigateAddCardSuccessful: () -> Unit
){
    var cardNumber by rememberSaveable(key = "addCardNumber"){ mutableStateOf("") }
    var holderName by rememberSaveable(key = "addCardHolderName"){ mutableStateOf("") }
    var expiryDate by rememberSaveable(key = "addCardExpiryDate"){ mutableStateOf("") }
    var cvv by rememberSaveable(key = "addCardCvv"){ mutableStateOf("") }

    var state by remember {
        mutableStateOf(CardFace.Front)
    }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp,20.dp,70.dp,10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AddCardContentH(
                    flippableCard = {
                        FlippableCard(
                            cardFace = state,
                            onClick = {
                                state = it.next
                            },
                            axis = RotationAxis.AxisY,
                            back = { NewCreditCardBack(cvv = cvv) },
                            front = {
                                NewCreditCardFront(
                                    cardNumber = formatNumber(cardNumber),
                                    holderName = formatName(holderName),
                                    expiryDate = expiryDate,
                                    logo = getCardType(cardNumber)
                                )
                            }
                        )
                    },
                    cardInputs = {
                        CardInputs(
                            cardNumber = cardNumber,
                            onCardNumberChange = { cardNumber = it },
                            holderName = holderName,
                            onHolderNameChange = { holderName = it },
                            expiryDate = expiryDate,
                            onExpiryDateChange = { expiryDate = it },
                            cvv = cvv,
                            onCvvChange = { cvv = it },
                            onStateChange = {state = it}
                        )
                    },
                    navigateAddCardSuccessful = navigateAddCardSuccessful
                )

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
                AddCardContentV(
                    flippableCard = {
                        FlippableCard(
                            cardFace = state,
                            onClick = {
                                state = it.next
                            },
                            axis = RotationAxis.AxisY,
                            back = { NewCreditCardBack(cvv = cvv) },
                            front = {
                                NewCreditCardFront(
                                    cardNumber = formatNumber(cardNumber),
                                    holderName = formatName(holderName),
                                    expiryDate = expiryDate,
                                    logo = getCardType(cardNumber)
                                )
                            }
                        )
                    },
                    cardInputs = {
                        CardInputs(
                            cardNumber = cardNumber,
                            onCardNumberChange = { cardNumber = it },
                            holderName = holderName,
                            onHolderNameChange = { holderName = it },
                            expiryDate = expiryDate,
                            onExpiryDateChange = { expiryDate = it },
                            cvv = cvv,
                            onCvvChange = { cvv = it },
                            onStateChange = {state = it}
                        )
                    },
                    navigateAddCardSuccessful = navigateAddCardSuccessful
                )
            }
        }
    }
}

@Composable
fun AddCardContentH(
    flippableCard: @Composable () -> Unit = {},
    cardInputs: @Composable () -> Unit = {},
    navigateAddCardSuccessful: () -> Unit
) {
    AppWindow (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Agregar tarjeta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth().weight(1f)
                        .height(200.dp),
                ) {
                    flippableCard()
                }
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cardInputs()
                }
            }
            AppButton(text = "Continuar", width = 0.5f, onClick = navigateAddCardSuccessful)
        }
    }
}

@Composable
fun AddCardContentV(
    flippableCard: @Composable () -> Unit = {},
    cardInputs: @Composable () -> Unit = {},
    navigateAddCardSuccessful: () -> Unit
) {
    AppWindow {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "Agregar tarjeta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                ) {
                    flippableCard()
                }
                cardInputs()
            }
            Spacer(Modifier.height(6.dp))
            AppButton(text = "Agregar", width = 0.8f, onClick = navigateAddCardSuccessful)
        }
    }
}


@Composable
fun CardInputs(
    cardNumber: String,
    onCardNumberChange: (String) -> Unit,
    holderName: String,
    onHolderNameChange: (String) -> Unit,
    expiryDate: String,
    onExpiryDateChange: (String) -> Unit,
    cvv: String,
    onCvvChange: (String) -> Unit,
    onStateChange: (CardFace) -> Unit
){
    AppInput(
        label = "Número de tarjeta",
        value = cardNumber,
        onValueChange = { if (it.length <= 16) onCardNumberChange(it) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onFocusAction = {onStateChange(if(it) CardFace.Front else CardFace.Back)}
    )
    AppInput(
        label = "Nombre del titular",
        value = holderName,
        onValueChange = {if (it.length <= 25) onHolderNameChange(it)},
        modifier = Modifier.fillMaxWidth(),
        onFocusAction = {onStateChange(if(it) CardFace.Front else CardFace.Back)}
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppInput(
            label = "Vencimiento",
            value = expiryDate,
            onValueChange = { if (it.length <= 5) onExpiryDateChange(it) },
            modifier = Modifier.fillMaxWidth(0.6f),
            placeholder = "MM/YY",
            onFocusAction = { onStateChange(if (it) CardFace.Front else CardFace.Back) }
        )
        AppInput(
            label = "CVV",
            value = cvv,
            onValueChange = { if (it.length <= 3) onCvvChange(it) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onFocusAction = { onStateChange(if (it) CardFace.Back else CardFace.Front) }
        )
    }
}

fun formatNumber(cardNumber: String): String {
    val input = cardNumber.replace("\\s+".toRegex(), "").replace("\\D".toRegex(), "")
    if (input.length > 16) {
        return input.substring(0, 16).chunked(4).joinToString(" ")
    }
    return input.chunked(4).joinToString(" ")
}

fun formatName(holderName: String): String {
    val input = holderName.replace("\n+".toRegex(), " ")
    return input.uppercase(Locale.ROOT)
}

fun getCardType(cardNumber: String): Int {
    val number = cardNumber.replace("\\s+".toRegex(), "").replace("\\D".toRegex(), "")

    // Visa
    if (number.matches(Regex("^4[0-9]*"))) {
        return R.drawable.visa
    }

    // AMEX
    if (number.matches(Regex("^3[47][0-9]*"))) {
        return R.drawable.amex
    }

    // Mastercard
    if (number.matches(Regex("^(?:5[1-5][0-9]{2}|222[1-9]|22[3-9][0-9]|2[3-6][0-9]{2}|27[01][0-9]|2720)[0-9]*"))) {
        return R.drawable.mastercard
    }

    return 0
}

