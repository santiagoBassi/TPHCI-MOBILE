package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow

@Preview(showBackground = true)
@Composable
fun ChangeAlias(
    navigateChangeAliasSuccessful: () -> Unit = {}
) {
    var newAlias by rememberSaveable(key = "newAlias") { mutableStateOf("") }

    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(50.dp, 20.dp, 100.dp, 15.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ChangeAliasContent(true, newAlias, { newAlias = it }, navigateChangeAliasSuccessful)
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
                ChangeAliasContent(false, newAlias, { newAlias = it }, navigateChangeAliasSuccessful)
            }
        }
    }
}

@Composable
fun ChangeAliasContent(
    landscape: Boolean = false,
    newAlias: String,
    onNewAliasChange: (String) -> Unit,
    navigateChangeAliasSuccessful: () -> Unit = {}
){
    AppWindow(
        modifier = Modifier.fillMaxHeight(if(landscape) 1f else 0.85f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.enter_new_alias),
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.spacedBy(if(landscape) 5.dp else 16.dp),
            ) {
                AppInput(value = newAlias, onValueChange = { onNewAliasChange(it) }, label = stringResource(R.string.new_alias),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = if(landscape) 100.dp else 8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Text(stringResource(R.string.rule1))
                Text(stringResource(R.string.rule2))
                if(landscape) {
                    Text(stringResource(R.string.rule3))
                } else {
                    Column {
                        Text(stringResource(R.string.rule3_1))
                        Text(stringResource(R.string.rule3_2))
                    }
                }
            }
            AppButton(text = stringResource(R.string.change_alias), onClick = navigateChangeAliasSuccessful, width = if(landscape) 0.6f else 0.8f)
        }

    }
}