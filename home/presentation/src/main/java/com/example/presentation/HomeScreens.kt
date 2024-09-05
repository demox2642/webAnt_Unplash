package com.example.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.detail.HomeDetailScreen
import com.example.presentation.main.HomeMainScreen

sealed class HomeScreens(
    val route: String,
) {
    object HomeMainScreen : HomeScreens("home_main_screen")

    object HomeDetailScreen : HomeScreens("home_detail_screen")
}

fun NavGraphBuilder.homeScreens(navController: NavHostController) {
    composable(HomeScreens.HomeMainScreen.route) { HomeMainScreen(navController) }
    composable(HomeScreens.HomeDetailScreen.route) { HomeDetailScreen(navController) }
}
