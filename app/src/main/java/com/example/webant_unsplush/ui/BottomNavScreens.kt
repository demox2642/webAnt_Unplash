package com.example.webant_unsplush.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.presentation.main.HomeMainScreen
import com.example.presentation.screens.PhotoScreen
import com.example.presentation.screens.ProfileScreen
import com.example.webant_unsplush.R

sealed class BottomNavScreens(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val nameId: Int,
) {
    object Home : BottomNavScreens("home_main_screen", com.example.base.presentation.R.drawable.ic_home, R.string.bottom_nav_home)

    object Photo : BottomNavScreens("photo_screen", com.example.base.presentation.R.drawable.ic_camera, R.string.bottom_nav_photo)

    object Profile : BottomNavScreens("profile_screen", com.example.base.presentation.R.drawable.ic_profile, R.string.bottom_nav_profile)
}

fun NavGraphBuilder.bottomNavScreen(navController: NavHostController) {
    composable(BottomNavScreens.Home.route) { HomeMainScreen(navController) }
    composable(BottomNavScreens.Photo.route) { PhotoScreen() }
    composable(BottomNavScreens.Profile.route) { ProfileScreen() }
}
