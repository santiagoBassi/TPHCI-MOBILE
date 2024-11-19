package com.lyrio.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R

@Preview(showBackground = true)
@Composable
fun LandingPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(80.dp),
                tint = DarkGray
            )
            Text(
                text = "Lyrio",
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "La nueva forma de",
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = DarkGray
            )
            Text(
                text = "administrar tu dinero",
                fontWeight = FontWeight.Black,
                fontSize = 26.sp,
                color = DarkGray
            )
            Spacer(modifier = Modifier.height(26.dp))
            Text(
                text = "Ahorrá, cobrá y pagá.",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lyrio te permite manejar tu dinero de",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "forma fácil y segura.",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
        }
        Image(
            painter = painterResource(id = R.drawable.landing_logo),
            contentDescription = "Landing page",
            modifier = Modifier
                .size(320.dp)
                .padding(vertical = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            AppButton(
                text = "Iniciar Sesión",
                onClick = { /*TODO*/ },
                width = 0.7f
            )
            AppButton(
                text = "Registrarse",
                onClick = { /*TODO*/ },
                width = 0.7f,
                background = LightGray
            )
        }
    }

}