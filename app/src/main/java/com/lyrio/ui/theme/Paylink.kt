package com.lyrio.ui.theme

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lyrio.R

@Preview(showBackground = true)
@Composable
fun PaylinkPreview(){
    Paylink(context = LocalContext.current)
}

@Composable
fun Paylink(context: Context) {

    val link = "https://lyrio.com/pay/1j8t90"

    LyrioTheme {
        Successful(
            message = "¡Link generado!",
            buttonLabel = "Volver al Inicio"
        ){
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(link, modifier = Modifier.weight(1f))
                IconButton(onClick = {
                    copyLink(context, link)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.copy),
                        contentDescription = "Copy",
                        tint = Color.Gray,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            AppButton(text = "Generar otro link", onClick = {}, width = 0.8f, background = LightGray)
        }
    }
}

@SuppressLint("ServiceCast")
fun copyLink(
    context: Context,
    link: String,
) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("Paylink", link)
    clipboardManager.setPrimaryClip(clipData)
}