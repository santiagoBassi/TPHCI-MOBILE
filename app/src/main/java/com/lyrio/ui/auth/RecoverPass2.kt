package com.lyrio.ui.auth

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.OffWhite

@Preview(showBackground = true)
@Composable
fun RecoverPass2() {
    var code by rememberSaveable(key = "recover2Code") { mutableStateOf("") }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxWidth().verticalScroll(rememberScrollState())
                    .height(800.dp)
                    .background(OffWhite),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AuthHeader()
                Column(
                    modifier = Modifier
                        .fillMaxHeight().fillMaxWidth(0.6f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RecoverPass2Content(
                        height = 0.82f,
                        landscape = true,
                        code = code,
                        onCodeChange = { code = it },
                    )
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
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RecoverPass2Content(
                        height = 0.7f,
                        code = code,
                        onCodeChange = { code = it },
                    )
                }

            }
        }
    }
}

@Composable
fun RecoverPass2Content(
    height: Float = 1f,
    landscape: Boolean = false,
    code: String,
    onCodeChange: (String) -> Unit
){
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
                text = "Recuperá tu contraseña",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                fontSize = if(landscape) 26.sp else 22.sp
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text("¡Ya te enviamos el correo!", fontWeight = FontWeight.Medium, fontSize = if(landscape) 22.sp else 18.sp)
                Spacer(modifier = Modifier.height(20.dp))
                Text("Revisá tu casilla de mail e ingresá", fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
                Text("el código de verificación para", fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
                Text("restablecer tu contraseña", fontWeight = FontWeight.Normal, fontSize = if(landscape) 20.sp else 16.sp)
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AppInput(
                    value = code,
                    onValueChange = { onCodeChange(it) },
                    label = "Código de verificación",
                    modifier = Modifier.fillMaxWidth(),
                )
            }
            AppButton(text = "Continuar", onClick = { /* TODO */ }, width = 0.8f)
        }
    }
}


