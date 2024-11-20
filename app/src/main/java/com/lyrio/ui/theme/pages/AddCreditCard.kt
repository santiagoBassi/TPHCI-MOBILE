package com.lyrio.ui.theme.pages

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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.theme.components.AppButton
import com.lyrio.ui.theme.components.AppInput
import com.lyrio.ui.theme.components.AppWindow
import com.lyrio.ui.theme.components.CardFace
import com.lyrio.ui.theme.components.FlippableCard
import com.lyrio.ui.theme.components.NewCreditCardBack
import com.lyrio.ui.theme.components.NewCreditCardFront
import com.lyrio.ui.theme.components.RotationAxis
import com.lyrio.ui.theme.styles.LyrioTheme
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun AddCreditCard(){
    var cardNumber by remember { mutableStateOf("") }
    var holderName by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    var state by remember {
        mutableStateOf(CardFace.Front)
    }

    LyrioTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
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
                        }
                        AppInput(
                            label = "Número de tarjeta",
                            value = cardNumber,
                            onValueChange = { if (it.length <= 16) cardNumber = it },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            onFocusAction = {state = if(it) CardFace.Front else CardFace.Back }
                        )
                        AppInput(
                            label = "Nombre del titular",
                            value = holderName,
                            onValueChange = {if (it.length <= 25) holderName = it},
                            modifier = Modifier.fillMaxWidth(),
                            onFocusAction = {state = if(it) CardFace.Front else CardFace.Back }
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ){
                            AppInput(
                                label = "Vencimiento",
                                value = expiryDate,
                                onValueChange = { if(it.length <= 5) expiryDate = it },
                                modifier = Modifier.fillMaxWidth(0.6f),
                                placeholder = "MM/YY",
                                onFocusAction = {state = if(it) CardFace.Front else CardFace.Back }
                            )
                            AppInput(
                                label = "CVV",
                                value = cvv,
                                onValueChange = {if(it.length <= 3) cvv = it},
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                                onFocusAction = { state = if(it) CardFace.Back else CardFace.Front }
                            )
                        }
                        Spacer(Modifier.height(6.dp))
                    }
                    AppButton(text = "Continuar", width = 0.8f, onClick = {})
                }

            }
        }
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

