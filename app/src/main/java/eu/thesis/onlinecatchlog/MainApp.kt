package eu.thesis.onlinecatchlog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.thesis.onlinecatchlog.screens.login.LoginView
import eu.thesis.onlinecatchlog.screens.main.MainView
import eu.thesis.onlinecatchlog.screens.splash.SplashScreen
import eu.thesis.onlinecatchlog.ui.theme.OnlineCatchLogTheme


@Composable
fun MainApp() {
    OnlineCatchLogTheme{
        Surface(color = MaterialTheme.colorScheme.background) {
            val appState = rememberAppState()

            Scaffold { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = SPLASH_SCREEN,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    notesGraph(appState)
                }
            }
        }
    }
}

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()) =
    remember(navController) {
        MainAppState(navController)
    }

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.notesGraph(appState: MainAppState) {
    composable(SIGN_IN_SCREEN) {
        LoginView(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }

    composable(MAIN_VIEW_SCREEN){
        MainView()
    }
    composable(SPLASH_SCREEN){
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
}
