package com.example.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette =
    lightColors(
        primary = MainDef,
        primaryVariant = MainDef,
        onPrimary = Color.White,
        secondary = AppGrayDef,
        secondaryVariant = GrayLightDef,
        onSecondary = Color.White,
        background = WhiteDef,
        onBackground = BlackDef,
        surface = Color.White,
        onSurface = Color.Red,
        error = ErrorRedDef,
        onError = WhiteDef,
    )

@Composable
fun UnsplushChatTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = LightColorPalette

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = colors.background,
    )

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content,
    )
}
