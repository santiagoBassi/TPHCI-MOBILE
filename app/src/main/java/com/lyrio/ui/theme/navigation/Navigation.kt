package com.lyrio.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lyrio.ui.theme.auth.SignIn
import com.lyrio.ui.theme.auth.SignUp1
import com.lyrio.ui.theme.auth.SignUp2
import com.lyrio.ui.theme.layout.DefaultLayout
import com.lyrio.ui.theme.pages.CreditCards
import com.lyrio.ui.theme.pages.Home
import com.lyrio.ui.theme.pages.Invest
import com.lyrio.ui.theme.pages.LandingPage
import com.lyrio.ui.theme.pages.Money
import com.lyrio.ui.theme.pages.Profile
import com.lyrio.ui.theme.pages.Transfer1
import com.lyrio.ui.theme.pages.Transfer2

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.Home){

        composable<Screen.Landing>{
            LandingPage(navigateSignIn = {
                navController.navigate(Screen.SignIn)
            }, navigateSignUp = {
                navController.navigate(Screen.SignUp1)
            })
        }

        composable< Screen.SignIn>{
            SignIn()
        }

        composable<Screen.SignUp1>{
            SignUp1()
        }

        composable<Screen.SignUp2>{
            SignUp2()
        }

        composable<Screen.Home>(){
            DefaultLayout(navController) {
                Home()
            }
        }

        composable<Screen.Profile>(){
            DefaultLayout(navController) {
                Profile()
            }
        }

        composable<Screen.Transfer1>(){
            DefaultLayout(navController) {
                Transfer1()
            }
        }

        composable<Screen.Transfer2>(){
            DefaultLayout(navController) {
                Transfer2()
            }
        }

        composable<Screen.Money>(){
            DefaultLayout(navController) {
                Money()
            }
        }

        composable<Screen.Movements>(){
            DefaultLayout(navController) {
                Money()
            }
        }

        composable<Screen.Invest>(){
            DefaultLayout(navController) {
                Invest()
            }
        }

        composable<Screen.CreditCards>(){
            DefaultLayout(navController) {
                CreditCards()
            }
        }
    }
}