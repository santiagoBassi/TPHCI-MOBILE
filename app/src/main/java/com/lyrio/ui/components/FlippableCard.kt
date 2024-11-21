package com.lyrio.ui.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
}

@ExperimentalMaterial3Api
@Composable
fun FlippableCard(
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    modifier: Modifier = Modifier,
    axis: RotationAxis = RotationAxis.AxisY,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing,
        ), label = ""
    )
    Card(
        onClick = { onClick(cardFace) },
        modifier = modifier
            .graphicsLayer {
                if (axis == RotationAxis.AxisX) {
                    rotationX = rotation.value
                } else {
                    rotationY = rotation.value
                }
                cameraDistance = 12f * density
            },
    ) {
        if (rotation.value <= 90f) {
            Box(
                Modifier.fillMaxSize().background(color = androidx.compose.ui.graphics.Color.White),
            ) {
                front()
            }
        } else {
            Box(
                Modifier
                    .fillMaxSize().background(color = androidx.compose.ui.graphics.Color.White)
                    .graphicsLayer {
                        if (axis == RotationAxis.AxisX) {
                            rotationX = 180f
                        } else {
                            rotationY = 180f
                        }
                    },
            ) {
                back()
            }
        }
    }
}