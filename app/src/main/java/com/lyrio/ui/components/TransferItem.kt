package com.lyrio.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.styles.Green
import java.text.NumberFormat
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Date
import java.util.Locale

@Composable
fun TransferItem(
    transactionType: String,
    amount: Double,
    recipient: String,
    date: Date
) {
    val formattedDate = remember { formatDate(date) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 4.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (transactionType == "Recibiste") R.drawable.arrow_left_bold
                        else R.drawable.arrow_right_bold
                    ),
                    contentDescription = null,
                    tint = if (transactionType == "Recibiste") Green else MaterialTheme.colorScheme.error,
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = NumberFormat.getCurrencyInstance(Locale("es", "AR"))
                        .format(kotlin.math.abs(amount)),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = if (transactionType == "Recibiste") Green else MaterialTheme.colorScheme.error
                    )
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${if (transactionType == "Recibiste") "de" else "a"} $recipient",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            thickness = 1.dp,
            modifier = Modifier
                .padding(top = 8.dp, bottom = 16.dp)
                .fillMaxWidth()
        )
    }
}


fun formatDate(date: Date): String {
    val instant = date.toInstant()
    val zoneId = ZoneId.systemDefault()
    val localDateTime = instant.atZone(zoneId).toLocalDateTime()
    val now = Instant.now().atZone(zoneId).toLocalDateTime()

    return if (ChronoUnit.DAYS.between(localDateTime, now) == 0L) {
        if (ChronoUnit.HOURS.between(localDateTime, now) == 0L) {
            "Recién"
        } else {
            "Hace ${ChronoUnit.HOURS.between(localDateTime, now)} horas"
        }
    } else {
        DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDateTime)
    }
}