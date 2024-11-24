package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.Successful

@Preview(showBackground = true)
@Composable
fun ChangeAliasSuccessful(
    navigateProfile: () -> Unit = {}
) {
    val configuration = LocalConfiguration.current

    val maxHeight = configuration.screenHeightDp.dp
    val maxWidth = configuration.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.4f else 0.7f)
                        .fillMaxHeight(if (isTablet) 0.6f else 0.8f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChangeAliasSuccessfulContent(height = 1f, navigateProfile)
                }
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if (isTablet) 0.7f else 1f)
                        .fillMaxHeight(if (isTablet) 0.8f else 1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChangeAliasSuccessfulContent(
                        navigateProfile = navigateProfile
                    )
                }
            }
        }
    }

}

@Composable
fun ChangeAliasSuccessfulContent(height: Float = 0.5f, navigateProfile: () -> Unit = {}){
    val fakeAlias = "mi.nuevo.alias"

    Successful(
        message = stringResource(R.string.alias_changed),
        buttonLabel = stringResource(R.string.back_profile),
        onClick = navigateProfile,
        height = height,
        content = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 6.dp).padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(stringResource(R.string.your_new_alias), color = Color.Gray, fontWeight = FontWeight.Medium)
                Text(" $fakeAlias", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)

            }
        }
    )
}