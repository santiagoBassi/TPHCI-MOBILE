package com.lyrio.ui.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.LyrioTheme
import com.lyrio.ui.styles.OffWhite

@Preview(showBackground = true)
@Composable
fun SignIn(){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LyrioTheme {
        AuthHeader {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(OffWhite)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppWindow(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.95f)
                        .fillMaxHeight(fraction = 0.9f)

                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceEvenly,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Iniciar sesión", style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            AppInput(
                                value = email,
                                onValueChange = { email = it },
                                label = "Email",
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                            )
                            AppInput(
                                value = password,
                                onValueChange = { password = it },
                                label = "Contraseña",
                                hint = "Debe tener al menos 8 caracteres.",
                                modifier = Modifier.fillMaxWidth(),
                                isPassword = true
                            )
                            Text(
                                text = "Olvidaste tu contraseña?",
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.padding(start = 8.dp),
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        AppButton(text = "Continuar", onClick = { /* TODO */ }, width = 0.8f)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("No tenés cuenta? ")
                            Text(
                                text = "Registrate",
                                textDecoration = TextDecoration.Underline,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}

