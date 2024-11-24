package com.lyrio.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.lyrio.R
import com.lyrio.ui.styles.DarkGray
import com.lyrio.ui.styles.OffWhite
import com.lyrio.ui.styles.Red

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    dismissText: String = stringResource(R.string.cancel),
    confirmText: String = ""
) {
    AlertDialog(
        title = {
            Text(text = dialogTitle, color = Color.Black)
        },
        text = {
            Text(text = dialogText, color = Color.Black)
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            if(confirmText.isNotEmpty()) {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(confirmText, color = Red)
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissText, color = DarkGray)
            }
        },
        containerColor = OffWhite
    )
}