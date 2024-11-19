package com.lyrio.ui.theme

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Profile() {
    var isEditing by remember { mutableStateOf(false) }
    var firstName by remember { mutableStateOf("Ezequiel") }
    var lastName by remember { mutableStateOf("Testoni") }
    var dni by remember { mutableStateOf("12345678") }
    var email by remember { mutableStateOf("etestoni@gmail.com") }
    var phone by remember { mutableStateOf("-") }
    var address by remember { mutableStateOf("-") }
    var birthDate by remember { mutableStateOf("-") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            AppWindow(
                modifier = Modifier.wrapContentWidth().padding(bottom = 16.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ){
                    Text(
                        text = "Bienvenido, $firstName!",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        item {
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
                    ){
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
                    ){
                        Text("Alias:")
                        Text("mi.alias.lyrio")
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ){
                        Text(
                            text = "Cambiar alias",
                            style = TextStyle(textDecoration = TextDecoration.Underline),
                            color = Color.Gray,
                            modifier = Modifier.clickable(enabled = false) {} // Deshabilitado por ahora
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

        item {
            AppWindow(title = "Datos personales", modifier = Modifier.padding(bottom = 16.dp)) {
                Column(modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                    AppInput(value = firstName, onValueChange = { firstName = it }, label = "Nombre", readOnly = !isEditing, modifier = Modifier.fillMaxWidth())
                    AppInput(value = lastName, onValueChange = { lastName = it }, label = "Apellido", readOnly = !isEditing, modifier = Modifier.fillMaxWidth())
                    AppInput(value = email, onValueChange = { email = it }, label = "Email", readOnly = !isEditing, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email), modifier = Modifier.fillMaxWidth())
                    AppInput(value = birthDate, onValueChange = { birthDate = it }, label = "Fecha de nacimiento", placeholder = "DD/MM/YYYY", readOnly = !isEditing, modifier = Modifier.fillMaxWidth())
                    Spacer(modifier = Modifier.height(16.dp))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AppButton(
                        text = if (isEditing) "Guardar cambios" else "Editar perfil",
                        onClick = { isEditing = !isEditing },
                        width = 0.8f,
                    )
                }
            }
        }
    }
}