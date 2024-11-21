package com.lyrio.ui.styles

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalContext
import com.lyrio.ui.layout.DefaultDimens
import com.lyrio.ui.layout.Dimens


private val localDimensions = staticCompositionLocalOf { DefaultDimens }

private val DarkColorScheme = darkColorScheme(
    primary = Orange,
    secondary = DarkGray,
    tertiary = LightGray,
    background = White,
    surface = OffWhite,
    error = Red,
)

private val LightColorScheme = lightColorScheme(
    primary = Orange,
    secondary = DarkGray,
    tertiary = LightGray,
    background = White,
    surface = OffWhite,
    error = Red,

)

@Composable
fun ProvideDimens(
    dimensions: Dimens,
    content: () -> Unit
){
    val dimensionSet = remember { dimensions }
    CompositionLocalProvider(localDimensions provides dimensionSet, content = content)
}

@Composable
fun LyrioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}