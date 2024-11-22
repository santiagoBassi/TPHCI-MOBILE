package com.lyrio.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lyrio.R

@Composable
fun CreditCard(
    modifier: Modifier = Modifier,
    cardNumber: String,
    logo: Int = 0,
    primaryColor: Color = Color(0xFF000000),
    secondaryColor: Color = Color(0xFF5f5f5f),
    logoSize: Dp = 80.dp,
    clickEnabled: Boolean = true,
    onClick: () -> Unit = {}
){
    var cardWidth by remember { mutableStateOf(0.dp) }

    Card(
        modifier = modifier
            .clickable (enabled = clickEnabled){ onClick() }
            .height(180.dp) // Ajusta la altura según tus necesidades
            .padding(16.dp)
            .onGloballyPositioned { coordinates ->
                cardWidth = coordinates.size.width.dp
            },
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(primaryColor, secondaryColor)
                    )
                )
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (cardWidth > 800.dp) {
                    if(logo != 0) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Image(
                                painter = painterResource(id = logo),
                                contentDescription = null,
                                modifier = Modifier.size(logoSize)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = formatCardNumber(cardNumber),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = stringResource(R.string.credit),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.credit),
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                        if(logo != 0) {
                            Image(
                                painter = painterResource(id = logo),
                                contentDescription = null,
                                modifier = Modifier.size(logoSize)
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = formatCardNumber(cardNumber),
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}


fun formatCardNumber(cardNumber: String): String {
    return cardNumber
        .replace("\\s".toRegex(), "")
        .replace("(\\d{4})(\\d+)".toRegex(), "$1 **** **** ****")
}

@Preview(showBackground = true)
@Composable
fun CreditCardPreview(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CreditCard(
            cardNumber = "4734 5678 9012 3456",
            logo = R.drawable.visa_white,
            primaryColor = Color(0xFF120269),
            secondaryColor = Color(0xFF2204C6),
            logoSize = 80.dp
        )
        CreditCard(
            cardNumber = "2345 5678 9012 3456",
            logo = R.drawable.mastercard,
            primaryColor = Color(0xFF000000),
            secondaryColor = Color(0xFF5f5f5f),
            logoSize = 80.dp
        )
    }
}