package com.lyrio.ui.theme

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R

@Preview(showBackground = true)
@Composable
fun ReceiveMoneyPreview(){
    ReceiveMoney(context = LocalContext.current)
}

@Composable
fun ReceiveMoney(context: Context){
    val cvu = "000000123019231200"
    val alias = "mi.alias.lyrio"

    LyrioTheme {
        Column(
            modifier = Modifier
                .fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppWindow(
                title = "Recibir dinero",
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(text = "Recibí dinero a través de tu CVU o Alias,")
                        Text(text = "o cobrá mediante un link de pago.")
                    }
                    AppWindow(
                        title = "CVU y Alias",
                        modifier = Modifier.padding(bottom = 16.dp),
                        background = Color(0xFFF5F5F5)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(12.dp)
                                .fillMaxWidth(),
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("CVU:")
                                Text(cvu)
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 6.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Alias:")
                                Text(alias)
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = "Copiar CVU y Alias",
                                    color = Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                IconButton(onClick = {
                                    copyCVUandAlias(context, cvu, alias)
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.copy),
                                        contentDescription = "Copy",
                                        tint = Color.Gray,
                                        modifier = Modifier.size(22.dp)
                                    )
                                }
                            }
                        }
                    }
                    AppButton(
                        text = "Generar link de pago",
                        onClick = { /* TODO */ },
                        width = 0.8f
                    )
                }
            }
        }
    }

}

@SuppressLint("ServiceCast")
fun copyCVUandAlias(
    context: Context,
    cvu: String,
    alias: String,
) {
    val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clipData = ClipData.newPlainText("CVU and Alias", "CVU: $cvu\nAlias: $alias")
    clipboardManager.setPrimaryClip(clipData)
}