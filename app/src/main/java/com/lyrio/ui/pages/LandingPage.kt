package com.lyrio.ui.pages

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
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
    var maxHeight = 550.dp
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp
    val isTablet = maxWidth > 1000.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp, 32.dp, 100.dp, 10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Top
        ) {
            Column(
                modifier = Modifier.weight(0.55f).fillMaxHeight(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp, 18.dp, 0.dp, 0.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    if(isTablet) Spacer(Modifier.height(20.dp))
                    Brand(if(isTablet) 1.4f else 1f)
                    if(isTablet) Spacer(Modifier.height(20.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth().weight(1f)
                            .padding(start = 25.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        LoreH(if(isTablet) 1.4f else 1f)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .weight(0.45f).fillMaxHeight()
                    .padding(bottom = 15.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier
                        .onSizeChanged { size -> if(size.height.dp > maxHeight) maxHeight = size.height.dp }
                        .fillMaxWidth().weight(1f),
                    horizontalAlignment = Alignment.End
                ) {
                    LandingImg(maxHeight)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp,12.dp, 12.dp,12.dp),
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
    val maxWidth = LocalConfiguration.current.screenWidthDp.dp
    val isTablet = maxWidth >= 800.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 40.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Brand(if (isTablet) 1.4f else 1f)
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Lore(if (isTablet) 1.4f else 1f)
        }
        LandingImg(if(isTablet) 0.6.times(maxWidth) else 0.8.times(maxWidth))
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
                width = if(isTablet) 0.5f else 0.7f
            )
            AppButton(
                text = stringResource(R.string.register_reflex),
                onClick = navigateSignUp,
                width = if(isTablet) 0.5f else 0.7f,
                background = LightGray
            )
        }
    }
}

@Composable
fun Brand(factor: Float = 1f){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(factor.times(80.dp)),
            tint = DarkGray
        )
        Text(
            text = "Lyrio",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = factor.times(60.sp),
            fontWeight = FontWeight.Bold,
            color = DarkGray
        )
    }
}

@Composable
fun Lore(
    factor: Float = 1f
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = factor.times(25.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.landing_1),
            fontWeight = FontWeight.Medium,
            fontSize = factor.times(24.sp),
            color = DarkGray
        )
        Text(
            text = stringResource(R.string.landing_2),
            fontWeight = FontWeight.Black,
            fontSize = factor.times(26.sp),
            color = DarkGray
        )
        Spacer(modifier = Modifier.height(26.dp))
    }
}

@Composable
fun LoreH(factor: Float = 1f) {

    Text(
        text = stringResource(R.string.landing_1),
        fontWeight = FontWeight.Medium,
        fontSize = factor.times(28.sp),
        color = DarkGray
    )
    Text(
        text = stringResource(R.string.landing_2),
        fontWeight = FontWeight.Black,
        fontSize = factor.times(30.sp),
        color = DarkGray
    )
    Spacer(modifier = Modifier.height(factor.times(26.dp)))
    Text(
        text = stringResource(R.string.landing_3),
        fontWeight = FontWeight.Medium,
        fontSize = factor.times(20.sp),
        color = Color.Gray,
        fontStyle = FontStyle.Italic
    )
    Spacer(modifier = Modifier.height(factor.times(8.dp)))
    Text(
        text = stringResource(R.string.landing_4),
        fontWeight = FontWeight.Medium,
        fontSize = factor.times(20.sp),
        color = Color.Gray,
        fontStyle = FontStyle.Italic
    )
    Text(
        text = stringResource(R.string.landing_5),
        fontWeight = FontWeight.Medium,
        fontSize = factor.times(20.sp),
        color = Color.Gray,
        fontStyle = FontStyle.Italic
    )
}

@Composable
fun LandingImg(size: Dp = 320.dp){
    Image(
        painter = painterResource(id = R.drawable.landing_logo),
        contentDescription = "Landing page",
        modifier = Modifier
            .size(size)
            .padding(vertical = 14.dp)
    )
}