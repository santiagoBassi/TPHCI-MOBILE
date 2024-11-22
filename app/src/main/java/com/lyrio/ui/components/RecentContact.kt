package com.lyrio.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lyrio.ui.styles.Orange

@Composable
fun RecentContact(
    firstName: String,
    lastName: String,
    cvu: String,
    alias: String
) {
    Box(
        modifier = Modifier.fillMaxWidth().clickable {
            /* TODO */
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).padding(top = 25.dp)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Row {
                Text(text = firstName, fontWeight = FontWeight.Bold, color = Color.Black)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = lastName, fontWeight = FontWeight.Bold, color = Color.Black)
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.End
            ) {
                Text(text = cvu, color = Color.Gray)
                Text(text = alias, color = Color.Gray)
            }

        }
    }
    HorizontalDivider(
        color = Orange,
        thickness = 1.dp,
        modifier = Modifier.fillMaxWidth()
    )
}