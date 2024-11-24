package com.lyrio.ui.layout


import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import androidx.window.core.layout.WindowWidthSizeClass
import com.lyrio.R
import com.lyrio.ui.data.viewmodels.ViewModel
import com.lyrio.ui.navigation.Screen

@Composable
fun DefaultLayout(
    navController: NavController,
    viewModel: ViewModel,
    content: @Composable () -> Unit,
) {

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            MobileBottomBarNavigation(navController,content)
        }
        else -> {
            if(isMobile()) MobileSidebarNavigation(navController, content)
            else TabletNavigation(viewModel = viewModel, navController = navController, content = content)
        }
    }
}
val routeToStringResMap = mapOf(
    "com.lyrio.ui.navigation.Screen.SignIn" to R.string.com_lyrio_ui_navigation_Screen_SignIn,
    "com.lyrio.ui.navigation.Screen.SignUp1" to R.string.com_lyrio_ui_navigation_Screen_SignUp1,
    "com.lyrio.ui.navigation.Screen.SignUp2" to R.string.com_lyrio_ui_navigation_Screen_SignUp2,
    "com.lyrio.ui.navigation.Screen.SignUp3" to R.string.com_lyrio_ui_navigation_Screen_SignUp3,
    "com.lyrio.ui.navigation.Screen.RecoverPass1" to R.string.com_lyrio_ui_navigation_Screen_RecoverPass1,
    "com.lyrio.ui.navigation.Screen.RecoverPass2" to R.string.com_lyrio_ui_navigation_Screen_RecoverPass2,
    "com.lyrio.ui.navigation.Screen.RecoverPass3" to R.string.com_lyrio_ui_navigation_Screen_RecoverPass3,
    "com.lyrio.ui.navigation.Screen.Landing" to R.string.com_lyrio_ui_navigation_Screen_Landing,
    "com.lyrio.ui.navigation.Screen.Home" to R.string.com_lyrio_ui_navigation_Screen_Home,
    "com.lyrio.ui.navigation.Screen.AddCardSuccessful" to R.string.com_lyrio_ui_navigation_Screen_AddCardSuccessful,
    "com.lyrio.ui.navigation.Screen.AddCreditCard" to R.string.com_lyrio_ui_navigation_Screen_AddCreditCard,
    "com.lyrio.ui.navigation.Screen.AddInvestment" to R.string.com_lyrio_ui_navigation_Screen_AddInvestment,
    "com.lyrio.ui.navigation.Screen.ChangeAlias" to R.string.com_lyrio_ui_navigation_Screen_ChangeAlias,
    "com.lyrio.ui.navigation.Screen.ChangeAliasSuccessful" to R.string.com_lyrio_ui_navigation_Screen_ChangeAliasSuccessful,
    "com.lyrio.ui.navigation.Screen.CreditCards" to R.string.com_lyrio_ui_navigation_Screen_CreditCards,
    "com.lyrio.ui.navigation.Screen.Invest" to R.string.com_lyrio_ui_navigation_Screen_Invest,
    "com.lyrio.ui.navigation.Screen.Money" to R.string.com_lyrio_ui_navigation_Screen_Money,
    "com.lyrio.ui.navigation.Screen.Movements" to R.string.com_lyrio_ui_navigation_Screen_Movements,
    "com.lyrio.ui.navigation.Screen.Paylink" to R.string.com_lyrio_ui_navigation_Screen_Paylink,
    "com.lyrio.ui.navigation.Screen.Profile" to R.string.com_lyrio_ui_navigation_Screen_Profile,
    "com.lyrio.ui.navigation.Screen.ReceiveMoney" to R.string.com_lyrio_ui_navigation_Screen_ReceiveMoney,
    "com.lyrio.ui.navigation.Screen.Transfer1" to R.string.com_lyrio_ui_navigation_Screen_Transfer1,
    "com.lyrio.ui.navigation.Screen.Transfer2" to R.string.com_lyrio_ui_navigation_Screen_Transfer2,
    "com.lyrio.ui.navigation.Screen.TransferSuccessful" to R.string.com_lyrio_ui_navigation_Screen_TransferSuccessful,
    "com.lyrio.ui.navigation.Screen.WithdrawInvestment" to R.string.com_lyrio_ui_navigation_Screen_WithdrawInvestment
)

fun getScreenLabel(navController: NavController, context: Context): String? {
    val route = navController.currentDestination?.route.toString()
    val stringResId = routeToStringResMap[route]
    return stringResId?.let { context.getString(it) }
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
            val screenLabel = getScreenLabel(navController, LocalContext.current) ?: "Unknown Screen"
                NavigationDrawer(drawerState = drawerState, navController = navController, content = {
                    Column{
                        StateBar(text = screenLabel)
                        content()
                    }
                })
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
fun TabletNavigation(viewModel: ViewModel, navController: NavController, content: @Composable () -> Unit) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .windowInsetsPadding(WindowInsets.statusBars)
    ) {
        val screenLabel = getScreenLabel(navController, LocalContext.current) ?: "Unknown Screen"
        LeftBarTablet(
            navController = navController,
            viewModel = viewModel
        )
        Column {
            StateBar(text = screenLabel)
            content()
        }

    }
}


