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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.data.DataSourceException
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
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(if (isTablet) 0.9f else 1f)
                            .fillMaxWidth(if (isTablet) 0.35f else 0.6f)
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
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(if (isTablet) 0.6f else 1f)
                            .fillMaxHeight(if (isTablet) 0.7f else 1f)
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
    val uiStateUser by viewModel.uiStateUser.collectAsState()


    var isPasswordError by rememberSaveable(key = "recoverPass3Error"){ mutableStateOf(false) }
    var isConfirmPasswordError by rememberSaveable(key = "confirmRecoverPass3ErrorMsg"){ mutableStateOf(false) }
    var passwordErrorMsg by rememberSaveable(key = "recoverPass3ErrorMsg"){ mutableIntStateOf(-1) }
    var confirmPasswordErrorMsg by rememberSaveable(key = "confirmRecoverPass3ErrorMsg"){ mutableIntStateOf(-1) }

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
                text = stringResource(R.string.reset_pass),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = if(landscape) 26.sp else 22.sp
            )
            if(uiStateUser.error != null) {
                val errorCode = uiStateUser.error?.code ?: DataSourceException.UNEXPECTED_ERROR_CODE
                Text(
                    text = stringResource(errorMap[errorCode]!!),
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(bottom = 22.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(stringResource(R.string.recover3_lore1), fontWeight = FontWeight.Normal, fontSize = if(landscape) 22.sp else 18.sp)
                    Text(stringResource(R.string.recover3_lore2), fontWeight = FontWeight.Normal, fontSize = if(landscape) 22.sp else 18.sp)
                }
                AppInput(
                    value = newPassword,
                    onValueChange = {
                        onNewPasswordChange(it)
                        isPasswordError = false
                                    },
                    label = stringResource(R.string.password),
                    error = if(passwordErrorMsg != -1) stringResource(passwordErrorMsg) else null,
                    isError = isPasswordError,
                    hint = stringResource(R.string.pass_rule),
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
                AppInput(
                    value = confirmPassword,
                    onValueChange = {
                        onConfirmPasswordChange(it)
                        isConfirmPasswordError = false
                                    },
                    label = stringResource(R.string.confirm_pass),
                    error = if(confirmPasswordErrorMsg != -1) stringResource(confirmPasswordErrorMsg) else null,
                    isError = isConfirmPasswordError,
                    modifier = Modifier.fillMaxWidth(),
                    isPassword = true
                )
            }
            AppButton(text = stringResource(R.string.save_pass), onClick = {
                try {
                    val onInvalidPassword: (Int) -> Unit = {
                        passwordErrorMsg = it
                        isPasswordError = it != -1
                    }
                    val onInvalidConfirmPassword: (Int) -> Unit = {
                        confirmPasswordErrorMsg = it
                        isConfirmPasswordError = it != -1
                    }
                    if(validateQueries(newPassword, confirmPassword, onInvalidPassword, onInvalidConfirmPassword)) {
                        viewModel.clearError()
                        viewModel.recoverPass2(newPassword, navigateSignIn)
                    }
                }catch (e: Exception){
                    println(e.message)
                }
            }, width = 0.8f)
        }
    }
}

private fun validateQueries(newPassword: String, confirmPassword: String, onInvalidPassword: (Int) -> Unit, onInvalidConfirmPassword: (Int) -> Unit): Boolean {
    val checkPass = validatePasswordSignUp(newPassword, onInvalidPassword)
    return validateConfirmPassword(newPassword, confirmPassword, onInvalidConfirmPassword) && checkPass
}

private val errorMap = mapOf(
    DataSourceException.DATA_ERROR to R.string.invalid_password,
    DataSourceException.UNAUTHORIZED_ERROR_CODE to R.string.unauthorized,
    DataSourceException.INTERNAL_SERVER_ERROR_CODE to R.string.internal_server_error,
    DataSourceException.CONNECTION_ERROR_CODE to R.string.connection_error,
    DataSourceException.UNEXPECTED_ERROR_CODE to R.string.unexpected_error
)