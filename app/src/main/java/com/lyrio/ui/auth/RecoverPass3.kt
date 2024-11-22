package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite


@Composable
fun RecoverPass3(
    navigateSignIn: () -> Unit = {},
    viewModel: UserViewModel
) {
    var newPassword by rememberSaveable(key = "recover3NewPassword") { mutableStateOf("") }
    var confirmPassword by rememberSaveable(key = "recover3ConfirmPassword") { mutableStateOf("") }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
                    .height(800.dp)
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
                    RecoverPass3Content(
                        height = 0.82f,
                        landscape = true,
                        newPassword = newPassword,
                        onNewPasswordChange = { newPassword = it },
                        confirmPassword = confirmPassword,
                        onConfirmPasswordChange = { confirmPassword = it },
                        viewModel = viewModel,
                        navigateSignIn = navigateSignIn
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
                    RecoverPass3Content(
                        height = 0.8f,
                        newPassword = newPassword,
                        onNewPasswordChange = { newPassword = it },
                        confirmPassword = confirmPassword,
                        onConfirmPasswordChange = { confirmPassword = it },
                        viewModel = viewModel,
                        navigateSignIn = navigateSignIn
                    )
                }

            }
        }
    }
}

@Composable
fun RecoverPass3Content(
    height: Float = 1f,
    landscape: Boolean = false,
    newPassword: String,
    onNewPasswordChange: (String) -> Unit,
    confirmPassword: String,
    onConfirmPasswordChange: (String) -> Unit,
    viewModel: UserViewModel,
    navigateSignIn: () -> Unit
){
    AppWindow(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(height)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Reestablecer contraseña",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = if(landscape) 26.sp else 22.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text("Ingresá tu nueva contraseña", fontWeight = FontWeight.Normal, fontSize = if(landscape) 22.sp else 18.sp)
                    Text("y volvé a iniciar sesión", fontWeight = FontWeight.Normal, fontSize = if(landscape) 22.sp else 18.sp)
                }
                AppInput(
                    value = newPassword,
                    onValueChange = { onNewPasswordChange(it) },
                    label = "Contraseña",
                    hint = "Debe tener al menos 8 caracteres.",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                AppInput(
                    value = confirmPassword,
                    onValueChange = { onConfirmPasswordChange(it) },
                    label = "Confirmá tu contraseña",
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
            }
            AppButton(text = "Guardar contraseña", onClick = {
                try {
                    viewModel.recoverPass2(newPassword)
                    navigateSignIn()
                }catch (e: Exception){
                    println(e.message)
                }
            }, width = 0.8f)
        }
    }
}

