package com.lyrio.ui.auth

import android.widget.DatePicker
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
import androidx.compose.material3.DatePicker
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose.snippets.components.DatePickerFieldToModal
import com.lyrio.LyrioApp
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.states.SignUpUiState
import com.lyrio.ui.data.viewmodels.SignUpViewModel
import com.lyrio.ui.layout.AuthHeader
import com.lyrio.ui.styles.LyrioTheme
import com.lyrio.ui.styles.OffWhite
import java.util.Date

@Composable
fun SignUp1(
    viewModel: SignUpViewModel = viewModel(factory = SignUpViewModel.provideFactory(LocalContext.current.applicationContext as LyrioApp)),
    navigateSignUp2: () -> Unit

){
    var selectedDate by remember { mutableStateOf<Long?>(null) }
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }

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
                            text = "Creá tu cuenta", style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            AppInput(
                                value = name,
                                onValueChange = { name = it },
                                label = "Nombre/s",
                                modifier = Modifier.fillMaxWidth(),
                            )
                            AppInput(
                                value = lastname,
                                onValueChange = { lastname = it },
                                label = "Apellido/s",
                                modifier = Modifier.fillMaxWidth(),
                            )
                            DatePickerFieldToModal()
//                            AppInput(
//                                value = birthDate,
//                                onValueChange = { birthDate = it },
//                                label = "Fecha de nacimiento",
//                                placeholder = "DD/MM/AAAA",
//                                modifier = Modifier.fillMaxWidth(),
//                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        AppButton(text = "Continuar", onClick = {
                            viewModel.completeForm1(name, lastname, Date(55))
                            navigateSignUp2() }, width = 0.8f)

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text("Ya tenés una cuenta? ")
                            Text(
                                text = "Iniciá sesión",
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