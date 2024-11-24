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
import androidx.compose.runtime.getValue
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
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite

@Composable
fun SignUp3(
    navigateSignIn: () -> Unit,
    viewModel: UserViewModel
) {
    var code by rememberSaveable(key = "recover2Code") { mutableStateOf("") }

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
                        SignUp3Content(
                            height = 0.82f,
                            landscape = true,
                            code = code,
                            onCodeChange = { code = it },
                            navigateSignIn = navigateSignIn,
                            viewModel = viewModel
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
                        SignUp3Content(
                            height = 0.7f,
                            code = code,
                            onCodeChange = { code = it },
                            navigateSignIn = navigateSignIn,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SignUp3Content(
    height: Float = 1f,
    landscape: Boolean = false,
    code: String,
    onCodeChange: (String) -> Unit,
    navigateSignIn: () -> Unit,
    viewModel: UserViewModel
){

    var isError by rememberSaveable { mutableStateOf(false) }
    var errorMsg by rememberSaveable { mutableStateOf(-1) }

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
                text = stringResource(R.string.verify_account_imperative),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = if(landscape) 26.sp else 22.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(stringResource(R.string.mail_sent), fontWeight = FontWeight.Medium, fontSize = if(landscape) 22.sp else 18.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text(stringResource(R.string.signup_lore1), fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
                Text(stringResource(R.string.signup_lore2), fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
                Text(stringResource(R.string.signup_lore3), fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = code,
                    onValueChange = {
                        onCodeChange(it)
                        isError = false
                                    },
                    label = stringResource(R.string.verification_code),
                    error = if(errorMsg != -1) stringResource(errorMsg) else null,
                    isError = isError,
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            AppButton(text = stringResource(R.string.verify_account), onClick = {
                try {
                    val onInvalidCode: (Int) -> Unit = {
                        errorMsg = it
                        isError = it != -1
                    }
                    if(validateCode(code, onInvalidCode)) {
                        viewModel.verifyEmail(code)
                        navigateSignIn()
                    }
                }catch (e : Exception){
                    println(e) //TODO()
                }


            }, width = 0.8f)
        }
    }
}

private fun validateCode(code: String, onInvalidCode: (Int) -> Unit): Boolean {
    if(code.isEmpty()) {
        onInvalidCode(R.string.empty_field)
        return false
    }
    onInvalidCode(-1)
    return true
}


