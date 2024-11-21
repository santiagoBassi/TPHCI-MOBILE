package com.lyrio.ui.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lyrio.ui.theme.auth.SignIn
import com.lyrio.ui.theme.auth.SignUp1
import com.lyrio.ui.theme.auth.SignUp2
import com.lyrio.ui.theme.layout.DefaultLayout
import com.lyrio.ui.theme.pages.Home
import com.lyrio.ui.theme.pages.LandingPage
import com.lyrio.ui.theme.pages.Profile

@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = Home){

        composable<Landing>{
            LandingPage(navigateSignIn = {
                navController.navigate(SignIn)
            }, navigateSignUp = {
                navController.navigate(SignUp1)
            })
        }

        composable<SignIn>{
            SignIn()
        }

        composable<SignUp1>{
            SignUp1()
        }

        composable<SignUp2>{
            SignUp2()
        }

        composable<Home>(){
            DefaultLayout(navController) {
                Home()
            }
        }

        composable<Profile>(){
            DefaultLayout(navController) {
                Profile()
            }
        }
    }
}