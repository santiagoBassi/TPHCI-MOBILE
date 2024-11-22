package com.lyrio.ui.components

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
import com.lyrio.ui.styles.Green
import com.lyrio.ui.styles.LightGray
import com.lyrio.ui.styles.LyrioTheme
import com.lyrio.ui.styles.Orange

@Composable
fun Successful(
    message: String,
    buttonLabel: String,
    variant: String = "",
    height: Float = 0.5f,
    onClick: () -> Unit = {},
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
                    .fillMaxHeight(fraction = height)
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
                    AppButton(text = buttonLabel, onClick = onClick, width = if (height == 1f) 0.6f else 0.8f,
                        background = if(variant == "secondary") LightGray else Orange)
                }
            }
        }
    }
}




