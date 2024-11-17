package com.lyrio.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AppButton(text: String, onClick: () -> Unit, width: Float = 1f) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(width),
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange,
            contentColor = Color.Black
        )
    ) {
        Text(text = text)
    }
}