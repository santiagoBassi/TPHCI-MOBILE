package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.remember
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.pages.Money


@Composable
fun SignIn(
    navigateSignUp: () -> Unit,
    navigateHome: () -> Unit,
    navigateRecoverPass1: () -> Unit,
    viewModel: UserViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }


    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
                    .height(700.dp)
                    .background(OffWhite),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()
                Column(
                    modifier = Modifier
                        .fillMaxHeight().fillMaxWidth(0.6f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignInContent(
                        height = 0.9f,
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        navigateSignUp = navigateSignUp,
                        navigateHome = navigateHome,
                        viewModel = viewModel,
                        navigateRecoverPass1 = navigateRecoverPass1
                    )
                }
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OffWhite),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    SignInContent(
                        height = 0.7f,
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it },
                        navigateSignUp = navigateSignUp,
                        navigateHome = navigateHome,
                        viewModel = viewModel,
                        navigateRecoverPass1 = navigateRecoverPass1
                    )
                }

            }
        }
    }
}

@Composable
fun SignInContent(
    height: Float = 1f,
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    viewModel: UserViewModel,
    navigateSignUp: () -> Unit,
    navigateHome: () -> Unit,
    navigateRecoverPass1: () -> Unit
){
    AppWindow(
       modifier = if(height < 1f) Modifier.fillMaxWidth().fillMaxHeight(height) else Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Iniciar sesión", style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = email,
                    onValueChange = { onEmailChange(it) },
                    label = "Email",
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                AppInput(
                    value = password,
                    onValueChange = {  onPasswordChange(it) },
                    label = "Contraseña",
                    hint = "Debe tener al menos 8 caracteres.",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                Text(
                    text = "Olvidaste tu contraseña?",
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp). clickable(onClick = navigateRecoverPass1),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = "Continuar", onClick = {
                try {
                    viewModel.login(email, password)
                    navigateHome()
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }, width = 0.8f)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("No tenés cuenta?  ")
                Text(
                    text = "Registrate",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = navigateSignUp)
                )
            }
        }
    }
}

