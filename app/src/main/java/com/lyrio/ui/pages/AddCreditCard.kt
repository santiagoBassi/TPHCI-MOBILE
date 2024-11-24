package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.auth.validateName
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
) {
    var cardNumber by rememberSaveable(key = "addCardNumber") { mutableStateOf("") }
    var holderName by rememberSaveable(key = "addCardHolderName") { mutableStateOf("") }
    var expiryDate by rememberSaveable(key = "addCardExpiryDate") { mutableStateOf("") }
    var cvv by rememberSaveable(key = "addCardCvv") { mutableStateOf("") }

    var isNumberError by rememberSaveable(key = "addCardNumberError") { mutableStateOf(false) }
    var isNameError by rememberSaveable(key = "addCardHolderNameError") { mutableStateOf(false) }
    var isExpiryError by rememberSaveable(key = "addCardExpiryDateError") { mutableStateOf(false) }
    var isCvvError by rememberSaveable(key = "addCardCvvError") { mutableStateOf(false) }
    var numberErrorMsg by rememberSaveable(key = "addCardNumberErrorMsg") { mutableIntStateOf(-1) }
    var nameErrorMsg by rememberSaveable(key = "addCardHolderNameErrorMsg") { mutableIntStateOf(-1) }
    var expiryErrorMsg by rememberSaveable(key = "addCardExpiryDateErrorMsg") { mutableIntStateOf(-1) }
    var cvvErrorMsg by rememberSaveable(key = "addCardCvvErrorMsg") { mutableIntStateOf(-1) }

    var state by remember {
        mutableStateOf(CardFace.Front)
    }

    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    val handleOnClick: () -> Unit = {
        try {
            val onInvalidNumber: (Int) -> Unit = {
                numberErrorMsg = it
                isNumberError = it != -1
            }
            val onInvalidName: (Int) -> Unit = {
                nameErrorMsg = it
                isNameError = it != -1
            }
            val onInvalidExpiry: (Int) -> Unit = {
                expiryErrorMsg = it
                isExpiryError = it != -1
            }
            val onInvalidCvv: (Int) -> Unit = {
                cvvErrorMsg = it
                isCvvError = it != -1
            }
            if(validateQueries(cardNumber, holderName, expiryDate, cvv, onInvalidNumber, onInvalidName, onInvalidExpiry, onInvalidCvv)) {
                navigateAddCardSuccessful()
                // TODO: api call
            }
        } catch (e: Exception) {
            // TODO
    }
}

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 1f)
                        .fillMaxHeight(if (isTablet) 0.6f else 1f)
                        .padding(16.dp),
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
                                onStateChange = { state = it },
                                isNameError = isNameError,
                                isNumberError = isNumberError,
                                isExpiryError = isExpiryError,
                                isCvvError = isCvvError,
                                nameErrorMsg = nameErrorMsg,
                                numberErrorMsg = numberErrorMsg,
                                expiryErrorMsg = expiryErrorMsg,
                                cvvErrorMsg = cvvErrorMsg,
                                setIsNameError = { isNameError = it },
                                setIsNumberError = { isNumberError = it },
                                setIsExpiryError = { isExpiryError = it },
                                setIsCvvError = { isCvvError = it }

                            )
                        },
                        isTablet = isTablet,
                        handleOnClick = handleOnClick
                    )

                }
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.8f else 1f)
                        .fillMaxHeight(if (isTablet) 0.6f else 1f)
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
                                onStateChange = { state = it },
                                isNameError = isNameError,
                                isNumberError = isNumberError,
                                isExpiryError = isExpiryError,
                                isCvvError = isCvvError,
                                nameErrorMsg = nameErrorMsg,
                                numberErrorMsg = numberErrorMsg,
                                expiryErrorMsg = expiryErrorMsg,
                                cvvErrorMsg = cvvErrorMsg,
                                setIsNameError = { isNameError = it },
                                setIsNumberError = { isNumberError = it },
                                setIsExpiryError = { isExpiryError = it },
                                setIsCvvError = { isCvvError = it }
                            )
                        },
                        handleOnClick = handleOnClick
                    )
                }
            }
        }
    }
}

@Composable
fun AddCardContentH(
    flippableCard: @Composable () -> Unit = {},
    cardInputs: @Composable () -> Unit = {},
    isTablet: Boolean = false,
    handleOnClick: () -> Unit
) {
    AppWindow (
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = if (isTablet) 16.dp else 0.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.add_card),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(15.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .height(200.dp),
                ) {
                    flippableCard()
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    cardInputs()
                }
            }
            AppButton(text = stringResource(R.string.continue_), width = 0.5f, onClick = handleOnClick)
        }
    }
}

@Composable
fun AddCardContentV(
    flippableCard: @Composable () -> Unit = {},
    cardInputs: @Composable () -> Unit = {},
    handleOnClick: () -> Unit
) {
    AppWindow {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.add_card),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
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
            AppButton(text = stringResource(R.string.add), width = 0.8f, onClick = handleOnClick)
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
    onStateChange: (CardFace) -> Unit,
    isNameError: Boolean,
    isNumberError: Boolean,
    isExpiryError: Boolean,
    isCvvError: Boolean,
    nameErrorMsg: Int,
    numberErrorMsg: Int,
    expiryErrorMsg: Int,
    cvvErrorMsg: Int,
    setIsNameError: (Boolean) -> Unit,
    setIsNumberError: (Boolean) -> Unit,
    setIsExpiryError: (Boolean) -> Unit,
    setIsCvvError: (Boolean) -> Unit
) {

    AppInput(
        label = stringResource(R.string.card_number),
        error = if(numberErrorMsg != -1) stringResource(numberErrorMsg) else null,
        isError = isNumberError,
        value = cardNumber,
        onValueChange = { if (it.length <= 16) {
            onCardNumberChange(it)
            setIsNumberError(false)
        } },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onFocusAction = {onStateChange(if(it) CardFace.Front else CardFace.Back)}
    )
    AppInput(
        label = stringResource(R.string.holder_name),
        error = if(nameErrorMsg != -1) stringResource(nameErrorMsg) else null,
        isError = isNameError,
        value = holderName,
        onValueChange = {if (it.length <= 35) {
            onHolderNameChange(it)
            setIsNameError(false)
        }},
        modifier = Modifier.fillMaxWidth(),
        onFocusAction = {onStateChange(if(it) CardFace.Front else CardFace.Back)}
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        AppInput(
            label = stringResource(R.string.expiry),
            error = if(expiryErrorMsg != -1) stringResource(expiryErrorMsg) else null,
            isError = isExpiryError,
            value = expiryDate,
            onValueChange = { if (it.length <= 5) {
                onExpiryDateChange(it)
                setIsExpiryError(false)
            } },
            modifier = Modifier.fillMaxWidth(0.6f),
            placeholder = stringResource(R.string.expiry_format),
            onFocusAction = { onStateChange(if (it) CardFace.Front else CardFace.Back) }
        )
        AppInput(
            label = stringResource(R.string.cvv),
            error = if(cvvErrorMsg != -1) stringResource(cvvErrorMsg) else null,
            isError = isCvvError,
            value = cvv,
            onValueChange = { if (it.length <= 3) {
                onCvvChange(it)
                setIsCvvError(false)
            } },
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

private fun validateQueries(cardNumber: String, holderName: String, expiryDate: String, cvv: String, onInvalidNumber: (Int) -> Unit, onInvalidName: (Int) -> Unit, onInvalidExpiry: (Int) -> Unit, onInvalidCvv: (Int) -> Unit): Boolean {
    val checkNumber = validateCardNumber(cardNumber, onInvalidNumber)
    val checkName = validateName(holderName, onInvalidName)
    val checkExpiry = validateExpiryDate(expiryDate, onInvalidExpiry)
    return validateCvv(cvv, onInvalidCvv) && checkNumber && checkName && checkExpiry
}

fun validateCvv(cvv: String, onInvalidCvv: (Int) -> Unit): Boolean {
    if (cvv.isEmpty()) {
        onInvalidCvv(R.string.empty_field)
        return false
    }
    if (!cvv.matches(Regex("\\d{3}"))) {
        onInvalidCvv(R.string.invalid_cvv)
        return false
    }
    onInvalidCvv(-1)
    return true
}

fun validateExpiryDate(expiryDate: String, onInvalidExpiry: (Int) -> Unit): Boolean {
    if (expiryDate.isEmpty()) {
        onInvalidExpiry(R.string.empty_field)
        return false
    }
    val regex = "^(0[1-9]|1[0-2])/(2[4-9]|[3-9][0-9])$".toRegex()

    if (regex.matches(expiryDate)) {
        val (month, year) = expiryDate.split("/")
        if (month.toInt() <= 12 && year.toInt() > 24) {
            onInvalidExpiry(-1)
            return true
        }
    }
    onInvalidExpiry(R.string.invalid_expiry)
    return false
}

fun validateCardNumber(cardNumber: String, onInvalidNumber: (Int) -> Unit): Boolean {
    if (cardNumber.isEmpty()) {
        onInvalidNumber(R.string.empty_field)
        return false
    }
    if (!cardNumber.matches(Regex("^(?:\\d{4}[ -]?){3}\\d{4}\$"))) {
        onInvalidNumber(R.string.invalid_card_number)
        return false
    }
    onInvalidNumber(-1)
    return true
}



