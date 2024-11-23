package com.lyrio.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.lyrio.ui.styles.DarkGray
import com.lyrio.ui.styles.OffWhite
import com.lyrio.ui.styles.Red

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    dismissText: String = "Cancelar",
    confirmText: String = "Confirmar"
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
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmText, color = Red)
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