package com.example.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
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
    composable(
        HomeScreens.HomeDetailScreen.route + "/{photoId}" + "/{tableName}",
        arguments =
            listOf(
                navArgument(name = "photoId") { NavType.StringType },
                navArgument(name = "tableName") { NavType.StringType },
            ),
    ) { backStackEntry ->
        val photoId = (backStackEntry.arguments?.getString("photoId") ?: return@composable)
        val tableName = (backStackEntry.arguments?.getString("tableName") ?: return@composable)
        HomeDetailScreen(navController, photoId, tableName)
    }
}
