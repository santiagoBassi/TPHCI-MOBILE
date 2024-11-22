package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.Successful

@Composable
fun AddCardSuccessful(
    navigateAddCardSuccessful: () -> Unit,
    navigateHome: () -> Unit
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
                AddCardSuccessfulContent(height = 1f, navigateAddCardSuccessful, navigateHome)
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
                AddCardSuccessfulContent(
                    navigateAddCardSuccessful = navigateAddCardSuccessful,
                    navigateHome = navigateHome
                )
            }
        }
    }
}

@Composable
fun AddCardSuccessfulContent(height: Float = 0.5f, navigateAddCardSuccessful: () -> Unit, navigateHome: () -> Unit) {
    Successful(
        message = "¡Tarjeta agregada!",
        buttonLabel = "Volver al Inicio",
        onClick = navigateHome,
        variant = "secondary",
        height = height
    ) {
        Spacer(Modifier.height(30.dp))
        AppButton(text = "Agregar otra tarjeta", onClick = navigateAddCardSuccessful, width = if(height == 1f) 0.6f else 0.8f)
    }
}