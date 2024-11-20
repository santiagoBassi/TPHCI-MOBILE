package com.lyrio.ui.theme.layout

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Dimens(
    val spacingSmall: Dp = 4.dp,
    val spacingMedium: Dp = 8.dp,
)

val DefaultDimens = Dimens()