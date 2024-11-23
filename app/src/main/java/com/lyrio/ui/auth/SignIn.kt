package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite
import com.lyrio.ui.data.viewmodels.UserViewModel

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

    val maxHeight = configuration.screenHeightDp.dp
    val maxWidth = configuration.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
                    .height(if(isTablet) maxHeight + 100.dp else maxHeight * 2),
                horizontalAlignment = Alignment.CenterHorizontally,
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
    var emailErrorMsg by rememberSaveable(key = "signin_error_msg_email") { mutableIntStateOf(-1) }
    var passwordErrorMsg by rememberSaveable(key = "signin_error_msg_password") { mutableIntStateOf(-1) }
    var isErrorEmail by rememberSaveable(key = "signin_is_error_email") { mutableStateOf(false) }
    var isErrorPassword by rememberSaveable(key = "signin_is_error_pass") { mutableStateOf(false) }

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
                text = stringResource(R.string.sign_in), style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = email,
                    onValueChange = {
                        onEmailChange(it)
                        if(isErrorEmail) isErrorEmail = false
                                    },
                    label = stringResource(R.string.email),
                    error = if(emailErrorMsg != -1) stringResource(emailErrorMsg) else null,
                    isError = isErrorEmail,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                )
                AppInput(
                    value = password,
                    onValueChange = {
                        onPasswordChange(it)
                        if(isErrorPassword) isErrorPassword = false
                                    },
                    label = stringResource(R.string.password),
                    error = if(passwordErrorMsg != -1) stringResource(passwordErrorMsg) else null,
                    isError = isErrorPassword,
                    hint = stringResource(R.string.pass_rule),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                Text(
                    text = stringResource(R.string.forgot_pass),
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.padding(start = 8.dp, top = 10.dp). clickable(onClick = navigateRecoverPass1),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = stringResource(R.string.continue_), onClick = {
                try {
                   val onInvalidEmail: (Int) -> Unit = {
                        emailErrorMsg = it
                        isErrorEmail = it != -1
                    }
                    val onInvalidPassword: (Int) -> Unit = {
                        passwordErrorMsg = it
                        isErrorPassword = it != -1
                    }
                    if(validateQueries(email, password, onInvalidEmail, onInvalidPassword)) {
                        viewModel.login(email, password)
                        navigateHome()
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }

            }, width = 0.8f)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.dont_have_account) + "  ")
                Text(
                    text = stringResource(R.string.register),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = navigateSignUp)
                )
            }
        }
    }
}

private fun validateQueries(email: String, password: String, onInvalidEmail: (Int) -> Unit, onInvalidPassword: (Int) -> Unit): Boolean {
    val checkEmail = validateEmail(email, onInvalidEmail)
    return validatePasswordSignIn(password, onInvalidPassword) && checkEmail
}

fun validateEmail(email: String, onInvalidEmail: (Int) -> Unit): Boolean {
    if(email.isEmpty()) {
        onInvalidEmail(R.string.empty_field)
        return false
    }
    if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        onInvalidEmail(R.string.invalid_email)
        return false
    }
    onInvalidEmail(-1)
    return true
}

fun validatePasswordSignIn(password: String, onInvalidPassword: (Int) -> Unit): Boolean {
    if(password.isEmpty()) {
        onInvalidPassword(R.string.empty_field)
        return false
    }
    onInvalidPassword(-1)
    return true
}

