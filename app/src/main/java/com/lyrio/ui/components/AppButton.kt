package com.lyrio.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.lyrio.ui.styles.Orange

@Composable
fun AppButton(text: String, modifier: Modifier = Modifier, onClick: () -> Unit, width: Float = 1f, background: Color = Orange) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(width).height(55.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = background,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, style = MaterialTheme.typography.titleMedium)
    }
}