package com.lyrio.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lyrio.ui.auth.SignIn
import com.lyrio.ui.auth.SignUp1
import com.lyrio.ui.auth.SignUp2
import com.lyrio.ui.layout.DefaultLayout
import com.lyrio.ui.pages.CreditCards
import com.lyrio.ui.pages.Home
import com.lyrio.ui.pages.Invest
import com.lyrio.ui.pages.LandingPage
import com.lyrio.ui.pages.Money
import com.lyrio.ui.pages.Movements
import com.lyrio.ui.pages.Profile
import com.lyrio.ui.pages.ReceiveMoney
import com.lyrio.ui.pages.Transfer1
import com.lyrio.ui.pages.Transfer2

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Screen.Landing){

        composable<Screen.Landing>{
            LandingPage(navigateSignIn = {
                navController.navigate(Screen.SignIn)
            }, navigateSignUp = {
                navController.navigate(Screen.SignUp1)
            })
        }

        composable< Screen.SignIn>{
            SignIn(navigateSignUp = {
                navController.navigate(Screen.SignUp1)
            })
        }

        composable<Screen.SignUp1>{
            SignUp1(navigateSignUp2 = {
                navController.navigate(Screen.SignUp2)
            })
        }

        composable<Screen.SignUp2>{
            SignUp2()
        }

        composable<Screen.Home>{
            DefaultLayout(navController) {
                Home()
            }
        }

        composable<Screen.Profile>{
            DefaultLayout(navController) {
                Profile()
            }
        }

        composable<Screen.Transfer1>{
            DefaultLayout(navController) {
                Transfer1()
            }
        }

        composable<Screen.Transfer2>{
            DefaultLayout(navController) {
                Transfer2()
            }
        }

        composable<Screen.Money>{
            DefaultLayout(navController) {
                Money()
            }
        }

        composable<Screen.Movements>{
            DefaultLayout(navController) {
                Movements()
            }
        }

        composable<Screen.Invest>{
            DefaultLayout(navController) {
                Invest()
            }
        }

        composable<Screen.CreditCards>{
            DefaultLayout(navController) {
                CreditCards()
            }
        }
    }
}