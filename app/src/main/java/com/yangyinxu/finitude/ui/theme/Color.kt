package com.yangyinxu.finitude.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF202020)

val LightBlue = Color(0xFFD7E8DE)

val RedOrange = Color(0xFFFFAB91)
val RedPink = Color(0xFFF48FB1)
val BabyBlue = Color(0xFF81DEEA)
val Violet = Color(0xFFCF94DA)
val LightGreen = Color(0xFFE7ED9B)

val Colors.postBgColor: Color
    @Composable
    get() = if (isLight) LightGray else DarkGray

val Colors.topAppBarContentColor: Color
    @Composable
    get() = if (isLight) DarkGray else LightGray

val Colors.topAppBarBackgroundColor: Color
    @Composable
    get() = if (isLight) LightGray else DarkGray