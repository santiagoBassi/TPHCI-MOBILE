package com.lyrio.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun Transfer1() {
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

    LyrioTheme {
        Column(
            modifier = Modifier.fillMaxSize().padding(vertical = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(
                modifier = Modifier.fillMaxWidth(0.9f).fillMaxHeight(0.45f).padding(bottom = 16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(text = "¿A quién le querés transferir?", style = Typography.titleLarge, fontWeight = FontWeight.SemiBold)
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
                modifier = Modifier.fillMaxWidth(0.9f).weight(1f)
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
    }
}

data class RecentContactData(
    val firstName: String,
    val lastName: String,
    val cvu: String,
    val alias: String
)