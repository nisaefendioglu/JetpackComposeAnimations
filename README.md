# Jetpack Compose Animations

Welcome to the **Jetpack Compose Animations** project! 🎨✨ This guide will help you understand how to create various animations using Jetpack Compose, providing easy-to-follow examples that showcase color changes, scaling, movement, and more.

## Overview

This project demonstrates how to create several types of animations with Jetpack Compose, including:
1. **Color Change Animation** - Smoothly transitions the color of a box.
2. **Scale Animation** - Changes the size of a box, creating a pulsing effect.
3. **Movement Animation** - Moves a box horizontally back and forth.
4. **Fan Animation** - Rotates an icon continuously like a fan.
5. **Heartbeat Animation** - Creates a heartbeat effect on a heart icon.
6. **Crossfade Animation** - Smoothly transitions between two different screens.

These examples use `Animatable`, `animateFloatAsState`, and other Compose tools to achieve delightful animation effects.

## Code Examples

### Color Change Animation

```kotlin
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
```

### Scale Animation
```kotlin
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
```
### Move Animation
```kotlin
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
```
### Fan Animation
```kotlin
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
```
### Heart Animation
```kotlin
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
```
### Crossfade Animation
```kotlin
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
```

## Screen
[Screen_recording_20241017_140141.webm](https://github.com/user-attachments/assets/0f5d07cb-f544-4def-9e24-4aded62a5fb7)

 Happy coding! 😊
