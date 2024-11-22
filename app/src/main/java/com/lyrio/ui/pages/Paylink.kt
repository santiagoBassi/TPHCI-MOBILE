package com.lyrio.ui.pages

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.Successful
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.styles.Orange

@Composable
fun Paylink(context: Context, navigateHome: () -> Unit = {}) {
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
                PaylinkContent(context, height = 1f, navigateHome)
            }
        }

        else -> { // Modo vertical u otras orientaciones
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                PaylinkContent(context, height = 0.6f, navigateHome)
            }
        }
    }
}

@Composable
fun PaylinkContent(context: Context, height: Float = 0.5f, navigateHome: () -> Unit = {}) {
    val link = "https://lyrio.com/pay/1j8t90"

    Successful(
        message = stringResource(R.string.link_generated),
        buttonLabel = stringResource(R.string.back_home),
        variant = "secondary",
        height = height,
        onClick = navigateHome,
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 18.dp, 0.dp, 22.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(link, modifier = if(height == 1f) Modifier else Modifier
                .weight(1f)
                .padding(start = 12.dp),
                fontWeight = FontWeight.SemiBold, fontSize = 18.sp, textAlign = TextAlign.Center)
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
        AppButton(text = stringResource(R.string.generate_another_link), onClick = {}, width = if(height == 1f) 0.6f else 0.8f, background = Orange)
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