package com.lyrio.ui.theme.layout


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import androidx.window.core.layout.WindowWidthSizeClass
import com.lyrio.ui.theme.navigation.AppDestinations

@Composable
fun DefaultLayout(content: @Composable () -> Unit) {
    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.TRANSFER) }

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            MobileBottomBarNavigation(content, currentDestination)
        }
        else -> {
            if(isMobile()) MobileSidebarNavigation(content, currentDestination)
            else TabletNavigation(content, currentDestination)
        }
    }
}

@Composable
fun MobileBottomBarNavigation(content: @Composable () -> Unit, currentDestination: String) {
    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            Header(onButtonClick = {
                scope.launch {
                    drawerState.apply {
                        if (isClosed) open() else close()
                    }
                }
            }, state = drawerState)
        },
        bottomBar = {
            BottomBar()
        }
        ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(innerPadding)
                .fillMaxHeight()
                .shadow(1.dp)
            ) {
                NavigationDrawer(drawerState = drawerState, content = content)
            }
    }
}

@Composable
fun MobileSidebarNavigation(content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Row (Modifier.fillMaxSize()) {
        LeftBarMobile(state = drawerState, onButtonClick = {
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        })


        NavigationDrawer(
            drawerState = drawerState,
            modifier = Modifier
                .fillMaxSize()
                .zIndex(-1f),
            content = {
                Box(Modifier.fillMaxSize()) {
                    content()
                }
            }
        )
    }
}


@Composable
fun TabletNavigation(content: @Composable () -> Unit) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        LeftBarTablet()
        content()
    }
}


