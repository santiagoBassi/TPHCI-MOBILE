package com.lyrio.ui.auth

import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite

@Composable
fun SignUp1(
    viewModel: UserViewModel,
    navigateSignUp2: () -> Unit,
    navigateSignIn: () -> Unit
){
    var birthDate by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }


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

                    SignUp1Content(
                        height = 0.82f,
                        birthDate = birthDate,
                        onBirthDateChange = { birthDate = it },
                        name = name,
                        onNameChange = { name = it },
                        lastname = lastname,
                        onLastnameChange = { lastname = it },
                        viewModel = viewModel,
                        navigateSignUp2 = navigateSignUp2,
                        navigateSignIn = navigateSignIn
                    )
                }
            }
        }

        else -> {
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
                    SignUp1Content(
                        height = 0.8f,
                        birthDate = birthDate,
                        onBirthDateChange = { birthDate = it },
                        name = name,
                        onNameChange = { name = it },
                        lastname = lastname,
                        onLastnameChange = { lastname = it },
                        viewModel = viewModel,
                        navigateSignUp2 = navigateSignUp2,
                        navigateSignIn = navigateSignIn
                    )
                }

            }
        }
    }
}

@Composable
fun SignUp1Content(
    height: Float = 1f,
    birthDate: String,
    onBirthDateChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    lastname: String,
    onLastnameChange: (String) -> Unit,
    viewModel: UserViewModel,
    navigateSignUp2: () -> Unit,
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
                text = "Creá tu cuenta", style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = name,
                    onValueChange = { onNameChange(it) },
                    label = "Nombre/s",
                    modifier = Modifier.fillMaxWidth(),
                )
                AppInput(
                    value = lastname,
                    onValueChange = { onLastnameChange(it) },
                    label = "Apellido/s",
                    modifier = Modifier.fillMaxWidth(),
                )

                AppInput(
                    value = birthDate,
                    onValueChange = { onBirthDateChange(it) },
                    label = "Fecha de nacimiento",
                    placeholder = "AAAA-MM-DD",
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = "Continuar", onClick = {
                viewModel.completeFormRegister1(firstName = name, lastName = lastname, birthDate = birthDate)
                navigateSignUp2()
            }, width = 0.8f)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text("Ya tenés una cuenta? ")
                Text(
                    text = "Iniciá sesión",
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = navigateSignIn)
                )
            }
        }
    }
}
