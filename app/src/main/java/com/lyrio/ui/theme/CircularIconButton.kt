package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun CircularIconButton(icon: Painter, text: String, onClickFn: () -> Unit = {}) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(
            onClick = onClickFn,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = MaterialTheme.colorScheme.primary)

        ) {
            Icon(painter = icon, contentDescription = text, modifier = Modifier.size(24.dp))
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = text, style = MaterialTheme.typography.labelSmall)
    }
}