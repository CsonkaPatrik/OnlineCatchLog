package eu.thesis.onlinecatchlog

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController

@Stable
class MainAppState(val navController: NavHostController) {
    fun navigateAndPopUp(route: String, popUp: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(popUp) { inclusive = true }
        }
    }

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }
}