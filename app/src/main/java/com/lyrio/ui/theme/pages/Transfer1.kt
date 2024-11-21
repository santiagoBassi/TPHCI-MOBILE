package com.lyrio.ui.theme.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.ui.theme.components.RecentContact
import com.lyrio.ui.theme.components.AppButton
import com.lyrio.ui.theme.components.AppInput
import com.lyrio.ui.theme.components.AppWindow
import com.lyrio.ui.theme.styles.Typography

@Preview(showBackground = true)
@Composable
fun Transfer1() {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Row(
                modifier = Modifier.fillMaxSize().padding(20.dp,25.dp,70.dp,15.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Transfer1Content()
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Transfer1Content()
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
fun Transfer1Content() {
    val recentContacts = remember { // Lista de contactos de ejemplo
        listOf(
            RecentContactData("Juan", "Pérez", "1234567890", "juan.perez"),
            RecentContactData("María", "González", "9876543210", "maria.gonzalez"),
            RecentContactData("Pedro", "Rodríguez", "5678901234", "pedro.rodriguez"),
            RecentContactData("Ana", "García", "0123456789", "ana.garcia"),
            RecentContactData("Luis", "Martínez", "4321098765", "luis.martinez")
        )
    }

    var cvuOrAlias by remember { mutableStateOf("") }

    AppWindow(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .widthIn(max = 380.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().defaultMinSize(100.dp,250.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿A quién le querés transferir?",
                style = Typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            AppInput(
                value = cvuOrAlias,
                onValueChange = { cvuOrAlias = it },
                label = "CVU o Alias",
                modifier = Modifier.fillMaxWidth(0.95f)
            )
            AppButton(
                text = "Continuar",
                onClick = { /* TODO */ },
                width = 0.8f
            )
        }
    }

    AppWindow(
        title = "Contactos Recientes",
        modifier = Modifier
            .fillMaxHeight()
            .widthIn(max = 450.dp)
            .defaultMinSize(450.dp,0.dp)
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