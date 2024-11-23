package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite

@Composable
fun SignUp2(
    viewModel: UserViewModel,
    navigateSignUp3: () -> Unit
){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val configuration = LocalConfiguration.current

    val maxHeight = configuration.screenHeightDp.dp
    val maxWidth = configuration.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
                    .height(if(isTablet) maxHeight + 100.dp else maxHeight * 2)
                    .background(OffWhite),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()
                Box(
                    modifier = Modifier.fillMaxSize().padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(if(isTablet) 0.9f else 1f)
                            .fillMaxWidth(if(isTablet) 0.35f else 0.6f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        SignUp2Content(
                            height = 0.82f,
                            email = email,
                            onEmailChange = { email = it },
                            password = password,
                            onPasswordChange = { password = it },
                            confirmPassword = confirmPassword,
                            onConfirmPasswordChange = { confirmPassword = it },
                            viewModel = viewModel,
                            navigateSignUp3 = navigateSignUp3
                        )
                    }
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
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.6f else 1f)
                            .fillMaxHeight(if (isTablet) 0.8f else 1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SignUp2Content(
                            height = 0.8f,
                            email = email,
                            onEmailChange = { email = it },
                            password = password,
                            onPasswordChange = { password = it },
                            confirmPassword = confirmPassword,
                            onConfirmPasswordChange = { confirmPassword = it },
                            viewModel = viewModel,
                            navigateSignUp3 = navigateSignUp3
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun SignUp2Content(
    height: Float = 1f,
    email: String = "",
    onEmailChange: (String) -> Unit = {},
    password: String = "",
    onPasswordChange: (String) -> Unit = {},
    confirmPassword: String = "",
    onConfirmPasswordChange: (String) -> Unit = {},
    viewModel: UserViewModel,
    navigateSignUp3: () -> Unit
) {
    val signUpUiState by viewModel.uiStateUser.collectAsState()

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
                text = stringResource(R.string.create_account), style = MaterialTheme.typography.titleLarge,
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
                    label = stringResource(R.string.email),
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                AppInput(
                    value = password,
                    onValueChange = { onPasswordChange(it) },
                    label = stringResource(R.string.password),
                    hint = stringResource(R.string.pass_rule),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                AppInput(
                    value = confirmPassword,
                    onValueChange = { onConfirmPasswordChange(it) },
                    label = stringResource(R.string.confirm_pass),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(text = stringResource(R.string.continue_), onClick = {
                try {
                    viewModel.register(
                        firstName = signUpUiState.firstName,
                        lastName = signUpUiState.lastName,
                        dateOfBirth = signUpUiState.dateOfBirth,
                        email = email,
                        password = password
                    )
                    navigateSignUp3()
                }catch (e : Exception){
                    e.printStackTrace()
                }

            }, width = 0.8f)
        }
    }
}