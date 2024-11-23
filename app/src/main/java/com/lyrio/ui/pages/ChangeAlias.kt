package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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

    val maxWidth = configuration.screenWidthDp.dp
    val maxHeight = configuration.screenHeightDp.dp
    val isTablet = maxWidth > 1000.dp || maxHeight > 1000.dp

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth(if(isTablet) 0.6f else 0.7f)
                        .fillMaxHeight(if(isTablet) 0.8f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChangeAliasContent(true, newAlias, { newAlias = it }, navigateChangeAliasSuccessful)
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
                        .fillMaxHeight(if (isTablet) 0.6f else 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ChangeAliasContent(
                        false,
                        newAlias,
                        { newAlias = it },
                        navigateChangeAliasSuccessful
                    )
                }
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
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppInput(value = newAlias, onValueChange = { onNewAliasChange(it) }, label = stringResource(R.string.new_alias),
                    modifier = Modifier.fillMaxWidth(if(landscape) 0.8f else 0.95f))
                Spacer(modifier = Modifier.height(if(landscape) 30.dp else 30.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(stringResource(R.string.rule1), color = Color.Black)
                    Text(stringResource(R.string.rule2), color = Color.Black)
                    if (landscape) {
                        Text(stringResource(R.string.rule3), color = Color.Black)
                    } else {
                        Column {
                            Text(stringResource(R.string.rule3_1), color = Color.Black)
                            Text(stringResource(R.string.rule3_2), color = Color.Black)
                        }
                    }
                }
            }
            AppButton(text = stringResource(R.string.change_alias), onClick = navigateChangeAliasSuccessful, width = if(landscape) 0.6f else 0.8f)
        }

    }
}