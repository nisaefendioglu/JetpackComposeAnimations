package com.nisaefendioglu.composeanimations

import androidx.compose.animation.Animatable
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text

@Composable
fun AnimationExamples() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        ColorChangeAnimation()
        ScaleAnimation()
        MoveAnimation()
        FanAnimation()
        HeartbeatAnimation()
        CrossfadeAnimation()
    }
}

@Composable
fun ColorChangeAnimation() {
    val backgroundColor = remember { Animatable(Color.Blue) }

    LaunchedEffect(Unit) {
        while (true) {
            backgroundColor.animateTo(
                targetValue = Color.Red,
                animationSpec = tween(durationMillis = 1000)
            )
            backgroundColor.animateTo(
                targetValue = Color.Blue,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor.value),
        contentAlignment = Alignment.Center
    ) {
        Text("Color", color = Color.White)
    }
}

@Composable
fun ScaleAnimation() {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(Unit) {
        while (true) {
            scale.animateTo(
                targetValue = 1.5f,
                animationSpec = tween(durationMillis = 500)
            )
            scale.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 500)
            )
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .scale(scale.value)
            .background(Color.DarkGray),
        contentAlignment = Alignment.Center
    ) {
        Text("Scale", color = Color.White)
    }
}

@Composable
fun MoveAnimation() {
    val offsetX = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        while (true) {
            offsetX.animateTo(
                targetValue = 200f,
                animationSpec = tween(durationMillis = 1000)
            )
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = tween(durationMillis = 1000)
            )
        }
    }

    Box(
        modifier = Modifier
            .size(100.dp)
            .offset(x = offsetX.value.dp)
            .background(Color.Magenta),
        contentAlignment = Alignment.Center
    ) {
        Text("Move", color = Color.White)
    }
}

@Composable
fun FanAnimation() {
    var isRotating by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(
        targetValue = if (isRotating) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = "FanAnimation"
    )

    Icon(
        painter = painterResource(id = R.drawable.ic_fan),
        contentDescription = "Fan",
        modifier = Modifier
            .size(100.dp)
            .clickable { isRotating = !isRotating }
            .rotate(rotation),
        tint = Color.Unspecified
    )
}

@Composable
fun HeartbeatAnimation() {
    var isBeating by remember { mutableStateOf(true) }

    val scale by animateFloatAsState(
        targetValue = if (isBeating) 1.2f else 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 700, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ), label = "HeartbeatAnimation"
    )

    Icon(
        painter = painterResource(id = R.drawable.ic_heart),
        contentDescription = "Heart",
        modifier = Modifier
            .size(100.dp)
            .scale(scale)
            .clickable { isBeating = !isBeating },
        tint = Color.Unspecified

    )
}

@Composable
fun CrossfadeAnimation() {
    var isFirst by remember { mutableStateOf(true) }

    Crossfade(targetState = isFirst, label = "CrossfadeAnimation") { screen ->
        if (screen) {
            Text("Screen 1", modifier = Modifier.clickable { isFirst = !isFirst })
        } else {
            Text("Screen 2", modifier = Modifier.clickable { isFirst = !isFirst })
        }
    }
}
