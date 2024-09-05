package com.example.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.screens.PhotoScreen

sealed class PhotoScreens(
    val route: String,
) {
    object PhotoScreen : PhotoScreens("photo_screen")
}

fun NavGraphBuilder.photoScreens(navController: NavHostController) {
    composable(PhotoScreens.PhotoScreen.route) { PhotoScreen() }
}
