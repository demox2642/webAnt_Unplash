package com.example.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.screens.ProfileScreen

sealed class ProfileScreens(
    val route: String,
) {
    object ProfileScreen : ProfileScreens("profile_screen")
}

fun NavGraphBuilder.profileScreens(navController: NavHostController) {
    composable(ProfileScreens.ProfileScreen.route) { ProfileScreen() }
}
