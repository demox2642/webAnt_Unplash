package com.example.presentation.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

class AppColors(
    black: Color,
    gray: Color,
    grayLight: Color,
    white: Color,
    main: Color,
    errorRed: Color,
    blue: Color,
    gradient:Brush,
    isLight: Boolean,
) {
    var black by mutableStateOf(black)
        private set
    var gray by mutableStateOf(gray)
        private set
    var grayLight by mutableStateOf(grayLight)
        private set
    var white by mutableStateOf(white)
        private set

    var main by mutableStateOf(main)
        private set
    var errorRed by mutableStateOf(errorRed)
        private set

    var blue by mutableStateOf(blue)
        private set

    var gradient by mutableStateOf(gradient)

    var isLight by mutableStateOf(isLight)
        internal set

    fun copy(
        black: Color = this.black,
        gray: Color = this.gray,
        grayLight: Color = this.grayLight,
        white: Color = this.white,
        main: Color = this.main,
        errorRed: Color = this.errorRed,
        blue: Color = this.blue,
        gradient:Brush = this.gradient,
        isLight: Boolean = this.isLight,
    ): AppColors =
        AppColors(
            black,
            gray,
            grayLight,
            white,
            main,
            errorRed,
            blue,
            gradient,
            isLight,
        )

    fun updateColorsFrom(other: AppColors) {
        black= other.black
        gray = other.gray
        grayLight = other.grayLight
        white = other.white
        main = other.main
        errorRed = other.errorRed
        blue = other.blue
        gradient = other.gradient
    }
}



private val Black = Color(0xFF1D1D1D)
private val AppGray = Color(0xFFBCBCBC)
private val GrayLight = Color(0xFFEEEEEF)
private val White = Color(0xFFFFFFFF)
private val Main = Color(0xFFCF497E)
private val ErrorRed = Color(0xFFED3E3E)
private val Blue = Color(0xFF409EFF)
private val Gradient = Brush.verticalGradient(
    colors = listOf(
        Color(0xFFE69633),
        Color(0xFFCF497E)
    )
)


fun appLightColors(
    black: Color = Black,
    gray: Color = AppGray,
    grayLight: Color = GrayLight,
    white: Color = White,
    main: Color = Main,
    errorRed: Color = ErrorRed,
    blue: Color = Blue,
    gradient:Brush = Gradient
): AppColors =
    AppColors(
        black = black,
        gray = gray,
        grayLight = grayLight,
        white = white,
        main = main,
        errorRed = errorRed,
        blue = blue,
        gradient = gradient,
        isLight = true,
    )

val LocalColors = staticCompositionLocalOf { appLightColors() }
