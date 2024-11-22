package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.styles.LightGray
import com.lyrio.ui.styles.Orange

@Preview(showBackground = true)
@Composable
fun Profile(
    navigateChangeAlias: () -> Unit = {}
) {
    var isEditing by rememberSaveable(key = "isEditing") { mutableStateOf(false) }
    var firstName by rememberSaveable(key = "firstName") { mutableStateOf("Ezequiel") }
    var lastName by rememberSaveable(key = "lastName") { mutableStateOf("Testoni") }
    var email by rememberSaveable(key = "email") { mutableStateOf("etestoni@gmail.com") }
    var birthDate by rememberSaveable(key = "birthDate") { mutableStateOf("-") }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp, 25.dp, 70.dp, 10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ){
                    Welcome(firstName)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ){
                    Column(
                        modifier = Modifier.weight(1f),
                    ){
                        CVUAlias{ navigateChangeAlias() }
                    }
                    Column(
                        modifier = Modifier.weight(1f),
                    ){
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
                            onEditClick = { isEditing = it }
                        )
                    }
                }
            }
        }
        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Welcome(firstName)
                CVUAlias { navigateChangeAlias() }
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
                    onEditClick = { isEditing = it }
                )
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
    onEditClick: (Boolean) -> Unit
){
    AppWindow(title = "Datos personales", modifier = Modifier.padding(bottom = 16.dp)) {
        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            AppInput(
                value = firstName,
                onValueChange = { onFirstNameChange(it) },
                label = "Nombre",
                readOnly = !isEditing,
                modifier = Modifier.fillMaxWidth()
            )
            AppInput(
                value = lastName,
                onValueChange = { onLastNameChange(it) },
                label = "Apellido",
                readOnly = !isEditing,
                modifier = Modifier.fillMaxWidth()
            )
            AppInput(
                value = email,
                onValueChange = { onEmailChange(it) },
                label = "Email",
                readOnly = !isEditing,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            AppInput(
                value = birthDate,
                onValueChange = { onBirthDateChange(it) },
                label = "Fecha de nacimiento",
                placeholder = "DD/MM/AAAA",
                readOnly = !isEditing,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            AppButton(
                text = if (isEditing) "Guardar cambios" else "Editar perfil",
                onClick = { onEditClick(!isEditing) },
                width = 0.8f,
                background = if (isEditing) Orange else LightGray
            )
        }
    }
}

@Composable
fun CVUAlias(
    navigateChangeAlias: () -> Unit = {}
){
    AppWindow(title = "CVU y Alias", modifier = Modifier.padding(bottom = 16.dp)) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("CVU:")
                Text("000000123019231200")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Alias:")
                Text("mi.alias.lyrio")
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Cambiar alias",
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
fun Welcome(firstName: String){
    AppWindow(
        modifier = Modifier
            .wrapContentWidth()
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
                Text(text = "Bienvenido, ", fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                Text(
                    text = firstName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Orange
                )
                Text(text = "!", fontSize = 22.sp, fontWeight = FontWeight.Medium)
            }

        }
    }
}