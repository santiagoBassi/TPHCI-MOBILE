package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lyrio.LyrioApp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite
import com.lyrio.ui.ViewModel


@Composable
fun SignIn(
    navigateSignUp: () -> Unit,
    viewModel: ViewModel = viewModel(factory = ViewModel.provideFactory(LocalContext.current.applicationContext as LyrioApp))
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
                        onPasswordChange = { password = it }
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
                                onValueChange = { email = it },
                                label = "Email",
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )
                            AppInput(
                                value = password,
                                onValueChange = { password = it },
                                label = "Contraseña",
                                hint = "Debe tener al menos 8 caracteres.",
                                modifier = Modifier.fillMaxWidth(),
                                isPassword = true
                            )
                            Text(
                                text = "Olvidaste tu contraseña?",
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(start = 8.dp),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        AppButton(
                            text = "Continuar",
                            onClick = { viewModel.login(email, password) },
                            width = 0.8f)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("No tenés cuenta? ")
                            //TODO: arreglar esto, deberia se un link o algo asi
                            Button(onClick = navigateSignUp) {
                                Text(
                                    text = "Registrate",
                                    textDecoration = TextDecoration.Underline,
                                    fontWeight = FontWeight.Bold,
                                )
                            }

                        }
                    }
                    SignInContent(
                        height = 0.7f,
                        email = email,
                        onEmailChange = { email = it },
                        password = password,
                        onPasswordChange = { password = it }
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
    onPasswordChange: (String) -> Unit
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
                    modifier = Modifier.padding(start = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = "Continuar", onClick = { /* TODO */ }, width = 0.8f)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("No tenés cuenta?  ")
                Text(
                    text = "Registrate",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

