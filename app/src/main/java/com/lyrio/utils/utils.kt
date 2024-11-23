package com.lyrio.utils

import java.text.DecimalFormat
import java.util.Locale


fun formatCurrencyWhole(value: Double): String {
    val wholePart = value.toLong()
    val decimalFormat = DecimalFormat("#,###")
    val formattedWholePart = decimalFormat.format(wholePart)
        .replace(',', '.')

    return "$$formattedWholePart"
}


fun getDecimalPart(value: Double): String {
    val decimalPart = value - value.toLong() // Obtén la parte decimal como Double
    return String.format(Locale.US, "%02d", (decimalPart * 100).toInt()) // Usa Locale.US o el que prefieras
}