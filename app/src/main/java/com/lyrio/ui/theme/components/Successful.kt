package com.lyrio.ui.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lyrio.ui.theme.styles.Green
import com.lyrio.ui.theme.styles.LightGray
import com.lyrio.ui.theme.styles.LyrioTheme
import com.lyrio.ui.theme.styles.Orange

@Composable
fun Successful(
    message: String,
    buttonLabel: String,
    variant: String = "",
    content: @Composable () -> Unit = {}
){

    LyrioTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            AppWindow(
                modifier = Modifier
                    .fillMaxWidth(fraction = 0.95f)
                    .fillMaxHeight(fraction = 0.5f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = message, style = MaterialTheme.typography.titleLarge,
                        color = Green)
                    content()
                    AppButton(text = buttonLabel, onClick = { /* TODO */ }, width = 0.8f,
                        background = if(variant == "secondary") LightGray else Orange)
                }
            }
        }
    }
}



