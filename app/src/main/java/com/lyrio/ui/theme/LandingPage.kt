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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lyrio.R

@Composable
fun LandingPage(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(48.dp)
            )
            Text(text = "Lyrio")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = "La nueva forma de")
            Text(text = "administrar tu dinero")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ahorrá, cobrá y pagá.")
            Text(text = "Lyrio te permite manejar tu dinero de")
            Text(text = "forma fácil y segura.")
            Spacer(modifier = Modifier.height(16.dp))
        }
        Image(
            painter = painterResource(id = R.drawable.landing_logo),
            contentDescription = "Landing page",
            modifier = Modifier
                .size(200.dp)
                .padding(vertical = 16.dp)
        )
        AppButton(
            text = "Iniciar Sesión",
            onClick = { /*TODO*/ },
            width = 0.5f
        )
        Spacer(modifier = Modifier.height(10.dp))
        AppButton(
            text = "Registrarse",
            onClick = { /*TODO*/ },
            width = 0.5f
        )
    }

}