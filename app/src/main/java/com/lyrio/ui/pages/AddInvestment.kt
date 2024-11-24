package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.styles.Orange

@Composable
fun AddInvestment(
    navigateInvest: () -> Unit
) {
    var amount by rememberSaveable(key = "investedAmount"){ mutableDoubleStateOf(0.0) }
    val availableBalance = 100000.0

    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.5f else 0.6f)
                        .fillMaxHeight(if (isTablet) 0.7f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddInvestmentContent(
                        amount = amount,
                        onAmountChange = { amount = it },
                        availableBalance = availableBalance,
                        navigateInvest = navigateInvest
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
                        .fillMaxWidth(if (isTablet) 0.7f else 1f)
                        .fillMaxHeight(if (isTablet) 0.6f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddInvestmentContent(
                        0.8f,
                        amount = amount,
                        onAmountChange = { amount = it },
                        availableBalance = availableBalance,
                        navigateInvest = navigateInvest
                    )
                }
            }
        }
    }
}

@Composable
fun AddInvestmentContent(
    height: Float = 1f,
    amount: Double,
    onAmountChange: (Double) -> Unit,
    availableBalance: Double,
    navigateInvest: () -> Unit
) {
    var isError by rememberSaveable(key = "investedAmountError"){ mutableStateOf(false) }
    var errorMsg by rememberSaveable(key = "investedAmountErrorMsg"){ mutableIntStateOf(-1) }

    AppWindow {
        Column(
            modifier = Modifier.fillMaxHeight(height),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.how_much_to_invest),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Text(
                stringResource(R.string.available_balance) + " $$availableBalance",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Gray,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 18.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "$$amount",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Orange
                )
                Box(
                    modifier = Modifier.fillMaxWidth(if (height == 1f) 0.6f else 0.85f),
                    contentAlignment = Alignment.Center
                ) {
                    AppInput(
                        value = if (amount == 0.0) "" else amount.toString(),
                        onValueChange = {
                            onAmountChange(it.toDoubleOrNull() ?: 0.0)
                            isError = false
                                        },
                        label = stringResource(R.string.amount),
                        error = if(errorMsg != -1) stringResource(errorMsg) else null,
                        isError = isError,
                        hint = stringResource(R.string.add_investment_note),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            AppButton(
                text = stringResource(R.string.invest),
                onClick = {
                    try {
                        val onInvalidAmount: (Int) -> Unit = {
                            errorMsg = it
                            isError = it != -1
                        }
                        if(validateAmount(amount, onInvalidAmount)) {
                            navigateInvest()
                        }
                    } catch (e: Exception){
                        // TODO: handle error
                    }
                } ,
                modifier = Modifier.fillMaxWidth(if (height == 1f) 0.5f else 0.7f)
            )
        }
    }
}

fun validateAmount(amount: Double, onInvalidAmount: (Int) -> Unit): Boolean {
    if (amount == 0.0) {
        onInvalidAmount(R.string.empty_field)
        return false
    }
    if (amount < 0) {
        onInvalidAmount(R.string.invalid_amount)
        return false
    }
    onInvalidAmount(-1)
    return true
}
