package com.lyrio.ui.layout


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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.window.core.layout.WindowWidthSizeClass
import com.lyrio.ui.theme.layout.BottomBar

@Composable
fun DefaultLayout(navController: NavController,content: @Composable () -> Unit) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            MobileBottomBarNavigation(navController,content)
        }
        else -> {
            if(isMobile()) MobileSidebarNavigation(navController, content)
            else TabletNavigation(content)
        }
    }
}

@Composable
fun MobileBottomBarNavigation(navController: NavController, content: @Composable () -> Unit) {
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
            BottomBar(navController)
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
                NavigationDrawer(drawerState = drawerState, navController = navController, content = content)
            }
    }
}

@Composable
fun MobileSidebarNavigation(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Row (Modifier.fillMaxSize()) {
        LeftBarMobile(state = drawerState,navController = navController, onButtonClick = {
            scope.launch {
                drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
        })


        NavigationDrawer(
            navController = navController,
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


