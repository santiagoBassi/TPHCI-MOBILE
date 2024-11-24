package com.lyrio.ui.auth

import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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

    val maxHeight = configuration.screenHeightDp.dp
    val maxWidth = configuration.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .height(if (isTablet) maxHeight + 100.dp else maxHeight * 2)
                    .background(OffWhite),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 16.dp),
                    contentAlignment = Alignment.Center
                ){
                    Column(
                        modifier = Modifier
                            .fillMaxHeight(if (isTablet) 0.9f else 1f)
                            .fillMaxWidth(if (isTablet) 0.35f else 0.6f)
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
        }

        else -> {
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

    var isErrorBirthDate by rememberSaveable(key = "signup_birthDate") { mutableStateOf(false) }
    var isErrorName by rememberSaveable(key = "signup_name") { mutableStateOf(false) }
    var isErrorLastname by rememberSaveable(key = "signup_lastname") { mutableStateOf(false) }
    var birthDateErrorMsg by rememberSaveable(key = "signup_birthDateErrorMsg") { mutableIntStateOf(-1) }
    var nameErrorMsg by rememberSaveable(key = "signup_nameErrorMsg") { mutableIntStateOf(-1) }
    var lastnameErrorMsg by rememberSaveable(key = "signup_lastnameErrorMsg") { mutableIntStateOf(-1) }


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
                    value = name,
                    onValueChange = {
                        onNameChange(it)
                        isErrorName = false
                                    },
                    label = stringResource(R.string.name_s),
                    error = if(nameErrorMsg != -1) stringResource(nameErrorMsg) else null,
                    isError = isErrorName,
                    modifier = Modifier.fillMaxWidth(),
                )
                AppInput(
                    value = lastname,
                    onValueChange = {
                        onLastnameChange(it)
                        isErrorLastname = false
                                    },
                    label = stringResource(R.string.surname_s),
                    error = if(lastnameErrorMsg != -1) stringResource(lastnameErrorMsg) else null,
                    isError = isErrorLastname,
                    modifier = Modifier.fillMaxWidth(),
                )
                AppInput(
                    value = birthDate,
                    onValueChange = {
                        onBirthDateChange(it)
                        isErrorBirthDate = false
                                    },
                    label = stringResource(R.string.birthdate),
                    error = if(birthDateErrorMsg != -1) stringResource(birthDateErrorMsg) else null,
                    isError = isErrorBirthDate,
                    placeholder = stringResource(R.string.date_format),
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AppButton(text = stringResource(R.string.continue_), onClick = {
                val onInvalidBirthDate: (Int) -> Unit = {
                    birthDateErrorMsg = it
                    isErrorBirthDate = it != -1
                }
                val onInvalidName: (Int) -> Unit = {
                    nameErrorMsg = it
                    isErrorName = it != -1
                }
                val onInvalidLastname: (Int) -> Unit = {
                    lastnameErrorMsg = it
                    isErrorLastname = it != -1
                }
                if(validateQueries(birthDate, name, lastname, onInvalidName, onInvalidLastname, onInvalidBirthDate)) {
                    viewModel.completeFormRegister1(firstName = name, lastName = lastname, birthDate = birthDate)
                    navigateSignUp2()
                }
            }, width = 0.8f)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(stringResource(R.string.already_have_account) + " ")
                Text(
                    text = stringResource(R.string.sign_in_imperative),
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable(onClick = navigateSignIn)
                )
            }
        }
    }
}

private fun validateQueries(birthDate: String, name: String, lastname: String, onInvalidName: (Int) -> Unit, onInvalidLastname: (Int) -> Unit, onInvalidBirthDate: (Int) -> Unit): Boolean {
    val checkBirthDate = validateBirthDate(birthDate, onInvalidBirthDate)
    val checkName = validateName(name, onInvalidName)
    return validateLastname(lastname, onInvalidLastname) && checkBirthDate && checkName
}

fun validateBirthDate(birthDate: String, onInvalidBirthDate: (Int) -> Unit): Boolean {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    try {
        val parsedDate = LocalDate.parse(birthDate, formatter)

        if (parsedDate.isAfter(LocalDate.now())) {
            onInvalidBirthDate(R.string.invalid_birthdate)
            return false
        }
        onInvalidBirthDate(-1)
        return true

    } catch (e: DateTimeParseException) {
        onInvalidBirthDate(R.string.invalid_birthdate)
        return false
    }
}

fun validateName(name: String, onInvalidName: (Int) -> Unit): Boolean {
    if(name.isEmpty()) {
        onInvalidName(R.string.empty_field)
        return false
    }
    onInvalidName(-1)
    return true
}

fun validateLastname(lastname: String, onInvalidLastname: (Int) -> Unit): Boolean {
    if(lastname.isEmpty()) {
        onInvalidLastname(R.string.empty_field)
        return false
    }
    onInvalidLastname(-1)
    return true
}


