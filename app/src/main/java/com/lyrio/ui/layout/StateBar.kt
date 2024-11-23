package com.lyrio.ui.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StateBar(text: String){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 16.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier
                .padding(start = 35.dp)

        )
    }
}