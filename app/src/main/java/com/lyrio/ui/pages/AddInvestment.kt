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
import androidx.compose.runtime.mutableLongStateOf
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
    var amount by rememberSaveable(key = "investedAmount"){ mutableLongStateOf(0) }
    val availableBalance = 100000.0

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp,20.dp,150.dp,15.dp),
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

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AddInvestmentContent(0.8f,
                    amount = amount,
                    onAmountChange = { amount = it },
                    availableBalance = availableBalance,
                    navigateInvest = navigateInvest
                )
            }
        }
    }
}

@Composable
fun AddInvestmentContent(
    height: Float = 1f,
    amount: Long,
    onAmountChange: (Long) -> Unit,
    availableBalance: Double,
    navigateInvest: () -> Unit
) {
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
                        value = if (amount.toInt() == 0) "" else amount.toString(),
                        onValueChange = { onAmountChange(it.toLongOrNull() ?: 0) },
                        label = stringResource(R.string.amount),
                        hint = stringResource(R.string.add_investment_note),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            AppButton(
                text = stringResource(R.string.invest),
                onClick = navigateInvest,
                modifier = Modifier.fillMaxWidth(if (height == 1f) 0.5f else 0.7f)
            )
        }
    }
}