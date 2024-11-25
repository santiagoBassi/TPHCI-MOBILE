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
import androidx.compose.ui.unit.dp
import com.lyrio.R
import com.lyrio.data.DataSourceException
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.components.AppInput
import com.lyrio.ui.components.AppWindow
import com.lyrio.ui.data.viewmodels.UserViewModel
import com.lyrio.ui.data.viewmodels.WalletViewModel


@Composable
fun ChangeAlias(
    navigateChangeAliasSuccessful: () -> Unit,
    walletViewModel: WalletViewModel,
    userViewModel: UserViewModel
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
                    ChangeAliasContent(
                        landscape = true,
                        newAlias = newAlias,
                        onNewAliasChange = { newAlias = it },
                        navigateChangeAliasSuccessful = navigateChangeAliasSuccessful,
                        walletViewModel = walletViewModel
                    )
                }
            }
        }

        else -> {
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
                        landscape = false,
                        newAlias = newAlias,
                        onNewAliasChange = { newAlias = it },
                        navigateChangeAliasSuccessful = navigateChangeAliasSuccessful,
                        walletViewModel = walletViewModel
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
    navigateChangeAliasSuccessful: () -> Unit = {},
    walletViewModel: WalletViewModel
){
    var isError by rememberSaveable(key = "changeAlias_isError") { mutableStateOf(false) }
    var errorMsg by rememberSaveable(key = "changeAlias_errorMsg") { mutableIntStateOf(-1) }

    val uiStateWallet by walletViewModel.uiStateWallet.collectAsState()

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
            if(uiStateWallet.error != null) {
                val errorCode = uiStateWallet.error?.code ?: DataSourceException.UNEXPECTED_ERROR_CODE
                Text(
                    text = stringResource(errorMap[errorCode]!!),
                    color = MaterialTheme.colorScheme.error
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppInput(
                    value = newAlias,
                    onValueChange = {
                        onNewAliasChange(it)
                        isError = false
                                    },
                    label = stringResource(R.string.new_alias),
                    error = if(errorMsg != -1) stringResource(errorMsg) else null,
                    isError = isError,
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
            AppButton(text = stringResource(R.string.change_alias), onClick = {
                val onInvalidAlias: (Int) -> Unit = {
                    errorMsg = it
                    isError = it != -1
                }
                if(validateAlias(newAlias, onInvalidAlias)) {
                    walletViewModel.clearError()
                    walletViewModel.updateAlias(newAlias, onSuccessfulUpdate =  navigateChangeAliasSuccessful)

                }

            }, width = if(landscape) 0.6f else 0.8f)
        }

    }
}

fun validateAlias(alias: String, onInvalidAlias: (Int) -> Unit): Boolean {
    //TODO: Implementar validación de alias con las limitaciones correctas
    if(alias.isEmpty()) {
        onInvalidAlias(R.string.empty_field)
        return false
    }
    val len = alias.length
    if(!alias.contains(".") || len < 6 || len > 20) {
        onInvalidAlias(R.string.invalid_alias)
        return false
    }
    onInvalidAlias(-1)
    return true
}

private val errorMap = mapOf(
    DataSourceException.DATA_ERROR to R.string.invalid_alias,
    DataSourceException.UNAUTHORIZED_ERROR_CODE to R.string.invalid_credentials,
    DataSourceException.NOT_FOUND_ERROR_CODE to R.string.user_not_found,
    DataSourceException.INTERNAL_SERVER_ERROR_CODE to R.string.internal_server_error,
    DataSourceException.CONNECTION_ERROR_CODE to R.string.connection_error,
    DataSourceException.UNEXPECTED_ERROR_CODE to R.string.unexpected_error
)