package com.lyrio.ui.pages

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel
import com.lyrio.ui.styles.LightGray
import com.lyrio.ui.styles.Orange
import com.lyrio.ui.styles.Red


@Composable
fun Profile(
    navigateChangeAlias: () -> Unit,
    viewModelUser: UserViewModel,
    viewModelWallet: WalletViewModel,
) {
    val userState by viewModelUser.uiStateUser.collectAsState()

    LaunchedEffect(Unit, userState.isAuthenticated) {
        if (userState.isAuthenticated) {
            viewModelUser.getCurrentUser()
        }
    }

    var firstName by rememberSaveable(key = "firstName") { mutableStateOf(userState.firstName) }
    var lastName by rememberSaveable(key = "lastName") { mutableStateOf(userState.lastName) }
    var isEditing by rememberSaveable(key = "isEditing") { mutableStateOf(false) }
    var email by rememberSaveable(key = "email") { mutableStateOf(userState.email) }
    var birthDate by rememberSaveable(key = "birthDate") { mutableStateOf(userState.dateOfBirth) }

    var maxWidth = 800.dp
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                contentAlignment = Alignment.Center,
            ){
                Column(
                    modifier = Modifier
                        .fillMaxHeight().padding(16.dp)
                        .fillMaxWidth(0.85f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .onSizeChanged { size -> if(size.width.dp > maxWidth) maxWidth = size.width.dp },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Welcome(firstName, 800.dp)
                    }
                    Row(
                        modifier = Modifier.width(maxWidth),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Column(
                            modifier = Modifier.weight(1f),
                        ) {
                            CVUAlias(
                                navigateChangeAlias = navigateChangeAlias,
                                viewModelWallet = viewModelWallet,
                                userViewModel = viewModelUser

                            )
                        }
                        Column(
                            modifier = Modifier.weight(1f),
                        ) {
                            PersonalData(
                                firstName = firstName,
                                onFirstNameChange = { firstName = it },
                                lastName = lastName,
                                onLastNameChange = { lastName = it },
                                email = email,
                                onEmailChange = { email = it },
                                birthDate = birthDate,
                                onBirthDateChange = { birthDate = it },
                                isEditing = isEditing,
                                onEditClick = { isEditing = it },
                                viewModelUser = viewModelUser
                            )
                        }
                    }
                }
            }
        }
        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Welcome(firstName)
                    CVUAlias(
                        viewModelWallet = viewModelWallet,
                        navigateChangeAlias = navigateChangeAlias,
                        userViewModel = viewModelUser
                    )

                    PersonalData(
                        firstName = firstName,
                        onFirstNameChange = { firstName = it },
                        lastName = lastName,
                        onLastNameChange = { lastName = it },
                        email = email,
                        onEmailChange = { email = it },
                        birthDate = birthDate,
                        onBirthDateChange = { birthDate = it },
                        isEditing = isEditing,
                        onEditClick = { isEditing = it },
                        viewModelUser
                    )
                }
            }
        }
    }
}

@Composable
fun PersonalData(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    lastName: String,
    onLastNameChange: (String) -> Unit,
    email: String,
    onEmailChange: (String) -> Unit,
    birthDate: String,
    onBirthDateChange: (String) -> Unit,
    isEditing: Boolean,
    onEditClick: (Boolean) -> Unit,
    viewModelUser: UserViewModel,
){
    val userState by viewModelUser.uiStateUser.collectAsState()
    LaunchedEffect(Unit, userState.isAuthenticated) {
        if (userState.isAuthenticated) {
            viewModelUser.getCurrentUser()
        }
    }
    AppWindow(title = stringResource(R.string.personal_data),
        modifier = Modifier.widthIn(300.dp,390.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppInput(
                    value = firstName,
                    onValueChange = { onFirstNameChange(it) },
                    label = stringResource(R.string.firstname),
                    readOnly = !isEditing,
                    modifier = Modifier.fillMaxWidth()
                )
                AppInput(
                    value = lastName,
                    onValueChange = { onLastNameChange(it) },
                    label = stringResource(R.string.lastname),
                    readOnly = !isEditing,
                    modifier = Modifier.fillMaxWidth()
                )
                AppInput(
                    value = email,
                    onValueChange = { onEmailChange(it) },
                    label = stringResource(R.string.email),
                    readOnly = !isEditing,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )
                AppInput(
                    value = birthDate,
                    onValueChange = { onBirthDateChange(it) },
                    label = stringResource(R.string.birthdate),
                    placeholder = stringResource(R.string.date_format),
                    readOnly = !isEditing,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                AppButton(
                    text = if (isEditing) stringResource(R.string.save_changes) else stringResource(
                        R.string.edit_profile
                    ),
                    onClick = { onEditClick(!isEditing) },
                    width = 0.8f,
                    background = if (isEditing) Orange else LightGray
                )
            }
        }
    }
}

@Composable
fun CVUAlias(
    navigateChangeAlias: () -> Unit,
    viewModelWallet: WalletViewModel,
    userViewModel: UserViewModel
){
    val walletState by viewModelWallet.uiStateWallet.collectAsState()
    val userState by userViewModel.uiStateUser.collectAsState()

    LaunchedEffect(Unit,userState.isAuthenticated) {
        if(userState.isAuthenticated)
            viewModelWallet.getWalletData()
    }

    AppWindow(title = stringResource(R.string.cvu_alias),
        modifier = Modifier.padding(bottom = 16.dp).widthIn(300.dp, 390.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.cvu), fontWeight = FontWeight.Bold, color = Color.Black)
                Text(walletState.cbu, color = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(stringResource(R.string.alias), fontWeight = FontWeight.Bold, color = Color.Black)
                Text(walletState.alias, color = Color.Black)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.change_alias),
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    color = Color.Gray,
                    modifier = Modifier.clickable { navigateChangeAlias() }
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Chevron",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun Welcome(firstName: String, maxWidth: Dp = 380.dp){
    AppWindow(
        modifier = Modifier
            .widthIn(300.dp, maxWidth)
            .padding(bottom = 16.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(R.string.welcome), fontSize = 22.sp, fontWeight = FontWeight.SemiBold, color = Color.Black)
                Text(
                    text = " $firstName",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                )
                Text(text = "!", fontSize = 22.sp, fontWeight = FontWeight.Medium, color = Color.Black)
            }
        }
    }
}