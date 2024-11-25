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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.data.DataSourceException
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
    var email by rememberSaveable(key = "signup_email") { mutableStateOf("") }
    var password by rememberSaveable(key = "signup_password") { mutableStateOf("") }
    var confirmPassword by rememberSaveable(key = "signup_confirmPassword") { mutableStateOf("") }

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
    val userUiState by viewModel.uiStateUser.collectAsState()

    var isErrorEmail by remember { mutableStateOf(false) }
    var isErrorPassword by remember { mutableStateOf(false) }
    var isErrorConfirmPassword by remember { mutableStateOf(false) }
    var errorMsgEmail by remember { mutableIntStateOf(-1) }
    var errorMsgPassword by remember { mutableIntStateOf(-1) }
    var errorMsgConfirmPassword by remember { mutableIntStateOf(-1) }

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
            if(userUiState.error != null) {
                val errorCode = userUiState.error?.code ?: DataSourceException.UNEXPECTED_ERROR_CODE
                Text(
                    text = stringResource(errorMap[errorCode]!!),
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = email,
                    onValueChange = {
                        onEmailChange(it)
                        isErrorEmail = false
                                    },
                    label = stringResource(R.string.email),
                    error = if(errorMsgEmail != -1) stringResource(errorMsgEmail) else null,
                    isError = isErrorEmail,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                AppInput(
                    value = password,
                    onValueChange = {
                        onPasswordChange(it)
                        isErrorPassword = false
                                    },
                    label = stringResource(R.string.password),
                    error = if(errorMsgPassword != -1) stringResource(errorMsgPassword) else null,
                    isError = isErrorPassword,
                    hint = stringResource(R.string.pass_rule),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                AppInput(
                    value = confirmPassword,
                    onValueChange = {
                        onConfirmPasswordChange(it)
                        isErrorConfirmPassword = false
                                    },
                    label = stringResource(R.string.confirm_pass),
                    error = if(errorMsgConfirmPassword != -1) stringResource(errorMsgConfirmPassword) else null,
                    isError = isErrorConfirmPassword,
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            AppButton(text = stringResource(R.string.continue_), onClick = {
                val onInvalidEmail: (Int) -> Unit = {
                    errorMsgEmail = it
                    isErrorEmail = it != -1
                }
                val onInvalidPassword: (Int) -> Unit = {
                    errorMsgPassword = it
                    isErrorPassword = it != -1
                }
                val onInvalidConfirmPassword: (Int) -> Unit = {
                    errorMsgConfirmPassword = it
                    isErrorConfirmPassword = it != -1
                }
                if(validateQueries(email, password, confirmPassword, onInvalidEmail, onInvalidPassword, onInvalidConfirmPassword)) {
                    viewModel.clearError()
                    viewModel.register(
                        firstName = userUiState.firstName,
                        lastName = userUiState.lastName,
                        dateOfBirth = userUiState.dateOfBirth,
                        email = email,
                        password = password,
                        onSuccessfulRegister = navigateSignUp3
                    )
                }
            }, width = 0.8f)
        }
    }
}

private val errorMap = mapOf(
    DataSourceException.DATA_ERROR to R.string.invalid_data,
    DataSourceException.INTERNAL_SERVER_ERROR_CODE to R.string.internal_server_error,
    DataSourceException.CONNECTION_ERROR_CODE to R.string.connection_error,
    DataSourceException.UNEXPECTED_ERROR_CODE to R.string.unexpected_error
)

private fun validateQueries(email: String, password: String, confirmPassword: String, onInvalidEmail: (Int) -> Unit, onInvalidPassword: (Int) -> Unit, onInvalidConfirmPassword: (Int) -> Unit): Boolean {
    val checkEmail = validateEmail(email, onInvalidEmail)
    val checkPassword = validatePasswordSignUp(password, onInvalidPassword)
    return validateConfirmPassword(confirmPassword, password, onInvalidConfirmPassword) && checkEmail && checkPassword
}

fun validatePasswordSignUp(password: String, onInvalidPassword: (Int) -> Unit): Boolean {
    if(password.isEmpty()) {
        onInvalidPassword(R.string.empty_field)
        return false
    }
    if(password.length < 8) {
        onInvalidPassword(R.string.invalid_password)
        return false
    }
    onInvalidPassword(-1)
    return true
}

fun validateConfirmPassword(confirmPassword: String, password: String, onInvalidConfirmPassword: (Int) -> Unit): Boolean {
    if(confirmPassword.isEmpty()) {
        onInvalidConfirmPassword(R.string.empty_field)
        return false
    }
    if(confirmPassword != password) {
        onInvalidConfirmPassword(R.string.pass_not_match)
        return false
    }
    onInvalidConfirmPassword(-1)
    return true
}