package com.example.education.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.education.screens.MainScreen
import com.example.education.screens.AppDetailsContent
import com.example.education.screens.apps


@Composable
fun RootNavHost(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainScreenDestination,
        modifier = modifier
    ) {
        // Экран списка приложений
        composable<MainScreenDestination> {
            MainScreen(
                modifier = modifier,
                onAppClick = { app ->
                    navController.navigate(
                        AppDetailsContentDestination(
                            appId = app.id
                        )
                    )
                }
            )
        }

        // Экран подробной информации
        composable<AppDetailsContentDestination> { backStackEntry ->

            val destination =
                backStackEntry.toRoute<AppDetailsContentDestination>()

            val selectedApp = apps.find {
                it.id == destination.appId
            }

            if (selectedApp != null) {
                AppDetailsContent(
                    app = selectedApp,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    onShowcaseAppClick = { app ->
                        navController.navigate(
                            AppDetailsContentDestination(
                                appId = app.id
                            )
                        )
                    }
                )
            }
        }
    }
}