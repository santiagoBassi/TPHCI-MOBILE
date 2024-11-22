package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.Successful
import com.lyrio.ui.components.AppButton

@Preview(showBackground = true)
@Composable
fun TransferSuccessful(
    navigateHome: () -> Unit = {},
    navigateTransfer1: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp,20.dp,100.dp,15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TransferSuccessfulContent(
                    height = 1f,
                    navigateHome = navigateHome,
                    navigateTransfer1 = navigateTransfer1
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
                TransferSuccessfulContent(
                    navigateHome = navigateHome,
                    navigateTransfer1 = navigateTransfer1
                )
            }
        }
    }
}

@Composable
fun TransferSuccessfulContent(height: Float = 0.5f, navigateHome: () -> Unit = {}, navigateTransfer1: () -> Unit = {}) {
    val amount = 1000

    Successful(message = stringResource(R.string.transfer_sent), buttonLabel = stringResource(R.string.back_home), onClick = navigateHome, variant = "secondary", height = height){
        Text("$$amount", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.SemiBold, color = Color.Black)
        Text(stringResource(R.string.to) + " Ezequiel Testoni", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold, color = Color.Gray)
        Spacer(Modifier.height(12.dp))
        AppButton(text = stringResource(R.string.new_transfer), onClick = navigateTransfer1, width = if(height == 1f) 0.6f else 0.8f)
    }
}