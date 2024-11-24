package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.auth.validateEmail
import com.lyrio.ui.components.RecentContact
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.PaymentsViewModel
import com.lyrio.ui.styles.Typography

@Composable
fun Transfer1(
    navigateTransfer2: () -> Unit,
    paymentsViewModel: PaymentsViewModel
) {
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp
    val maxHeight = LocalConfiguration.current.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    Transfer1Content(
                        navigateTransfer2 = navigateTransfer2,
                        paymentsViewModel = paymentsViewModel
                    )
                }
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Transfer1Content(
                    isTablet,
                    navigateTransfer2,
                    paymentsViewModel
                )
            }
        }
    }
}

data class RecentContactData(
    val firstName: String,
    val lastName: String,
    val cvu: String,
    val alias: String
)

@Composable
fun Transfer1Content(
    isTablet: Boolean = false,
    navigateTransfer2: () -> Unit,
    paymentsViewModel: PaymentsViewModel
) {
    val recentContacts = remember { // Lista de contactos de ejemplo
        listOf(
            RecentContactData("Juan", "Pérez", "1234567890", "juan.perez"),
            RecentContactData("María", "González", "9876543210", "maria.gonzalez"),
            RecentContactData("Pedro", "Rodríguez", "5678901234", "pedro.rodriguez"),
            RecentContactData("Ana", "García", "0123456789", "ana.garcia"),
            RecentContactData("Luis", "Martínez", "4321098765", "luis.martinez")
        )
    }

    var email by rememberSaveable(key = "transferEmail") { mutableStateOf("") }

    var isError by rememberSaveable(key = "transferEmailError") { mutableStateOf(false) }
    var errorMsg by rememberSaveable(key = "transferEmailErrorMsg") { mutableIntStateOf(-1) }

    AppWindow(
        modifier = Modifier
            .padding(bottom = 20.dp)
            .widthIn(max = if(isTablet) 450.dp else 380.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().defaultMinSize(minHeight = 250.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.who_to_transfer),
                style = Typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            AppInput(
                value = email,
                onValueChange = {
                    email = it
                    isError = false
                                },
                error = if(errorMsg != -1) stringResource(errorMsg) else null,
                isError = isError,
                label = stringResource(R.string.email),
                modifier = Modifier.fillMaxWidth(0.95f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            AppButton(
                text = stringResource(R.string.continue_),
                onClick = {
                    try {
                        val onInvalidEmail: (Int) -> Unit = {
                            errorMsg = it
                            isError = it != -1
                        }
                        if(validateEmail(email, onInvalidEmail)){
                            paymentsViewModel.setReceiver(email)
                            navigateTransfer2()
                        }
                    } catch (e: Exception) {
                        // TODO: error handling
                    }

                },
                width = 0.8f
            )
        }
    }

    AppWindow(
        title = stringResource(R.string.recent_contacts),
        modifier = Modifier
            .fillMaxHeight(if(isTablet) 0.7f else 1f)
            .widthIn(350.dp, 450.dp)
    ) {
        LazyColumn(
            modifier = Modifier.padding(top = 5.dp)
        ) {

            items(recentContacts) { contact ->
                RecentContact(
                    firstName = contact.firstName,
                    lastName = contact.lastName,
                    cvu = contact.cvu,
                    alias = contact.alias
                )
            }
        }
    }
}