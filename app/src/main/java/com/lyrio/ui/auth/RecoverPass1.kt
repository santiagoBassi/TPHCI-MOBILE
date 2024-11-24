package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite

@Composable
fun RecoverPass1(
    navigateRecoverPass2: () -> Unit = {},
    viewModel: UserViewModel
) {
    var email by rememberSaveable(key = "recover1Email") { mutableStateOf("") }

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
                        RecoverPass1Content(
                            height = 0.82f,
                            landscape = true,
                            email = email,
                            onEmailChange = { email = it },
                            viewModel = viewModel,
                            navigateRecoverPass2 = navigateRecoverPass2

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
                            .fillMaxHeight(if (isTablet) 0.8f else 1f)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        RecoverPass1Content(
                            height = 0.7f,
                            email = email,
                            onEmailChange = { email = it },
                            viewModel = viewModel,
                            navigateRecoverPass2 = navigateRecoverPass2
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun RecoverPass1Content(
    height: Float = 1f,
    landscape: Boolean = false,
    email: String,
    onEmailChange: (String) -> Unit,
    viewModel: UserViewModel,
    navigateRecoverPass2: () -> Unit
){
    var isError by rememberSaveable(key = "recover1EmailError") { mutableStateOf(false) }
    var errorMsg by rememberSaveable(key = "recover1EmailErrorMsg") { mutableIntStateOf(-1) }

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
                text = stringResource(R.string.recover_pass),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = if(landscape) 26.sp else 22.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(stringResource(R.string.recover_lore1), fontWeight = FontWeight.Normal,
                    fontSize = if(landscape) 20.sp else 16.sp)
                Text(stringResource(R.string.recover_lore2), fontWeight = FontWeight.Normal,
                    fontSize = if(landscape) 20.sp else 16.sp)
                Text(stringResource(R.string.recover_lore3), fontWeight = FontWeight.Normal,
                    fontSize = if(landscape) 20.sp else 16.sp)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = email,
                    onValueChange = {
                        onEmailChange(it)
                        isError = false
                                    },
                    label = stringResource(R.string.email),
                    error = if(errorMsg != -1) stringResource(errorMsg) else null,
                    isError = isError,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
            }
            AppButton(text = stringResource(R.string.send_code), onClick = {
                try {
                    val onInvalidEmail: (Int) -> Unit = {
                        errorMsg = it
                        isError = it != -1
                    }
                    if(validateEmail(email, onInvalidEmail)) {
                        viewModel.recoverPass1(email)
                        navigateRecoverPass2()
                    }
                }catch (e: Exception){
                    e.printStackTrace()
                }
            }, width = 0.8f)
        }
    }
}


