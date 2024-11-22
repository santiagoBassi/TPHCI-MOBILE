package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.ui.components.Successful

@Preview(showBackground = true)
@Composable
fun ChangeAliasSuccessful(
    navigateProfile: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp,20.dp,100.dp,15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChangeAliasSuccesfulContent(height = 1f, navigateProfile)
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
                ChangeAliasSuccesfulContent(
                    navigateProfile = navigateProfile
                )
            }
        }
    }

}

@Composable
fun ChangeAliasSuccesfulContent(height: Float = 0.5f, navigateProfile: () -> Unit = {}){
    val fakeAlias = "mi.nuevo.alias"

    Successful(
        message = "¡Cambiaste tu alias!",
        buttonLabel = "Volver al Perfil",
        onClick = navigateProfile,
        height = height,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp).padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text("Tu nuevo alias es: ", color = Color.Gray, fontWeight = FontWeight.Medium)
                Text(fakeAlias, fontSize = 20.sp, fontWeight = FontWeight.Bold)

            }
        }
    )
}