package com.example.webant_unsplush

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.presentation.homeScreens
import com.example.presentation.photoScreens
import com.example.presentation.profileScreens
import com.example.presentation.theme.AppTheme
import com.example.presentation.theme.AppTheme.AppTheme
import com.example.presentation.theme.appLightColors
import com.example.webant_unsplush.ui.BottomNavScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, true)
        window.statusBarColor = Color.Black.toArgb()

        setContent {
            AppTheme(colors = appLightColors()) {
                val systemUiController = rememberSystemUiController()
                val useDarkIcons = true

                DisposableEffect(systemUiController, useDarkIcons) {
                    systemUiController.setSystemBarsColor(
                        color = Color.White,
                        darkIcons = useDarkIcons,
                    )

                    onDispose {}
                }
                Surface(color = MaterialTheme.colors.background) {
                    MainContent()
                }
            }
        }
    }
}

val showNavBar =
    listOf(
        BottomNavScreens.Home,
        BottomNavScreens.Photo,
        BottomNavScreens.Profile,
    ).map { it.route }

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun MainContent() {
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = AppTheme.colors.white,
        bottomBar = {
            Column {
                Divider(modifier = Modifier.fillMaxWidth(), color = AppTheme.colors.gray)
                BottomNav(navController)
            }
        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreens.Home.route,
            modifier = Modifier.padding(innerPadding),
        ) {
            homeScreens(navController)
            photoScreens(navController)
            profileScreens(navController)
        }
    }
}

@Composable
fun BottomNav(navController: NavHostController) {
    val items =
        listOf(
            BottomNavScreens.Home,
            BottomNavScreens.Photo,
            BottomNavScreens.Profile,
        )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    if (currentDestination?.route in showNavBar) {
        BottomNavigation(
            backgroundColor = AppTheme.colors.white,
            modifier =
                Modifier
                    .defaultMinSize(minWidth = 1.dp)
                    .height(72.dp),
            elevation = 0.dp,
        ) {
            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        androidx.compose.material.Icon(
                            painterResource(screen.icon),
                            contentDescription = null,
                        )
                    },
                    selectedContentColor = AppTheme.colors.main,
                    unselectedContentColor = AppTheme.colors.gray,
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                )
            }
        }
    }
}
