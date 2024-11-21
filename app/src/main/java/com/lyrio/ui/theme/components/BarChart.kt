package com.lyrio.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.lyrio.ui.theme.styles.Orange

@Composable
fun BarChart(data: List<BarChartData>, maxBarHeight: Dp) {
    val maxValue = data.maxOfOrNull { it.expense } ?: 0f
    val barWidth = 40.dp

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        items(data) { item ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(
                    modifier = Modifier
                        .width(barWidth)
                        .height(item.expense * maxBarHeight / maxValue)
                        .background(Orange, RoundedCornerShape(8.dp))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.label)
            }
        }
    }
}

data class BarChartData(val label: String, val expense: Float)