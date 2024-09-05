package com.example.presentation.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.base.presentation.R

private val robotoBolt = FontFamily(Font(R.font.roboto_bold))
private val robotoRegular = FontFamily(Font(R.font.roboto_regular))
private val robotoMedium = FontFamily(Font(R.font.roboto_medium))

data class AppTypography(
    val h1: TextStyle =
        TextStyle(
            fontFamily = robotoBolt,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            lineHeight = 36.sp,
        ),
    val h2: TextStyle =
        TextStyle(
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
        ),
    val h3: TextStyle =
        TextStyle(
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        ),
    val h4: TextStyle =
        TextStyle(
            fontFamily = robotoMedium,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
    val body1: TextStyle =
        TextStyle(
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
    val caption: TextStyle =
        TextStyle(
            fontFamily = robotoRegular,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
)

val Typography =
    Typography(
        defaultFontFamily = robotoRegular,
        h1 =
            TextStyle(
                fontFamily = robotoBolt,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                lineHeight = 36.sp,
            ),
        h2 =
            TextStyle(
                fontFamily = robotoRegular,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                lineHeight = 28.sp,
            ),
        h3 =
            TextStyle(
                fontFamily = robotoRegular,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 24.sp,
            ),
        h4 =
            TextStyle(
                fontFamily = robotoMedium,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            ),
        body1 =
            TextStyle(
                fontFamily = robotoRegular,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 22.sp,
            ),
        caption =
            TextStyle(
                fontFamily = robotoRegular,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
    )

internal val LocalTypography = staticCompositionLocalOf { AppTypography() }
