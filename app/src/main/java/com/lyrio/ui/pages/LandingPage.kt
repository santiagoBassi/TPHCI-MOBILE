package com.lyrio.ui.pages

import android.content.res.Configuration
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R
import com.lyrio.ui.components.AppButton
import com.lyrio.ui.styles.DarkGray
import com.lyrio.ui.styles.LightGray


@Composable
fun LandingPage(navigateSignIn: () -> Unit = {}, navigateSignUp: () -> Unit) {
    val configuration = LocalConfiguration.current

    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> { // Modo horizontal
            LandingContentH(
                navigateSignIn = navigateSignIn,
                navigateSignUp = navigateSignUp
            )
        }

        else -> { // Modo vertical u otras orientaciones
            LandingContentV(
                navigateSignIn = navigateSignIn,
                navigateSignUp = navigateSignUp
            )
        }
    }
}

@Composable
fun LandingContentH(
    navigateSignIn: () -> Unit = {},
    navigateSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp, 32.dp, 100.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(0.55f),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 8.dp, 0.dp, 0.dp),
                ){
                    Brand()
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp, 20.dp, 0.dp, 0.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LoreH()
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.45f)
                    .padding(bottom = 15.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.45f),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.End
                ) {
                    LandingImg()
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButton(
                        text = stringResource(R.string.sign_in),
                        onClick = navigateSignIn,
                        modifier = Modifier.weight(1f)
                    )
                    AppButton(
                        text = stringResource(R.string.register_reflex),
                        onClick = navigateSignUp,
                        background = LightGray,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun LandingContentV(
    navigateSignIn: () -> Unit = {},
    navigateSignUp: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Brand()
        Lore()
        LandingImg()
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        )
        {
            AppButton(
                text = stringResource(R.string.sign_in),
                onClick = navigateSignIn,
                width = 0.7f
            )
            AppButton(
                text = stringResource(R.string.register_reflex),
                onClick = navigateSignUp,
                width = 0.7f,
                background = LightGray
            )
        }
    }
}

@Composable
fun Brand(){
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
}

@Composable
fun Lore(){
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
}

@Composable
fun LoreH(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "La nueva forma de",
            fontWeight = FontWeight.Medium,
            fontSize = 28.sp,
            color = DarkGray
        )
        Text(
            text = "administrar tu dinero",
            fontWeight = FontWeight.Black,
            fontSize = 30.sp,
            color = DarkGray
        )
        Spacer(modifier = Modifier.height(26.dp))
        Text(
            text = "Ahorrá, cobrá y pagá.",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Lyrio te permite manejar tu dinero de",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "forma fácil y segura.",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun LandingImg(){
    Image(
        painter = painterResource(id = R.drawable.landing_logo),
        contentDescription = "Landing page",
        modifier = Modifier
            .size(320.dp)
            .padding(vertical = 14.dp)
    )
}