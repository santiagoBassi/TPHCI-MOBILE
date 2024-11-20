package com.lyrio.ui.theme.layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


@Composable
fun DefaultLayout(
    content: @Composable () -> Unit,
    onButtonClick: () -> Unit,
    windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
) {
    val showBottomAppBar = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    Scaffold(
        topBar = {
            Header(onButtonClick)
        },
        bottomBar = {
            if (showBottomAppBar) {
                BottomBar()
            }
            else {
                LeftBar()
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            content()
        }
    }
}