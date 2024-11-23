package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.Successful

@Composable
fun AddCardSuccessful(
    navigateAddCardSuccessful: () -> Unit,
    navigateHome: () -> Unit
) {
    val configuration = LocalConfiguration.current

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.7f else 0.6f)
                        .fillMaxHeight(if (isTablet) 0.6f else 1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddCardSuccessfulContent(height = 1f, navigateAddCardSuccessful, navigateHome, isTablet)
                }
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.7f else 1f)
                        .fillMaxHeight(if (isTablet) 0.8f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AddCardSuccessfulContent(
                        navigateAddCardSuccessful = navigateAddCardSuccessful,
                        navigateHome = navigateHome
                    )
                }
            }
        }
    }
}

@Composable
fun AddCardSuccessfulContent(height: Float = 0.5f, navigateAddCardSuccessful: () -> Unit, navigateHome: () -> Unit, isTablet: Boolean = false) {
    Successful(
        message = stringResource(R.string.card_added),
        buttonLabel = stringResource(R.string.back_home),
        onClick = navigateHome,
        variant = "secondary",
        height = height
    ) {
        Spacer(Modifier.height(if(isTablet) 100.dp else 30.dp))
        AppButton(text = stringResource(R.string.add_another_card), onClick = navigateAddCardSuccessful, width = if(height == 1f) 0.6f else 0.8f)
    }
}