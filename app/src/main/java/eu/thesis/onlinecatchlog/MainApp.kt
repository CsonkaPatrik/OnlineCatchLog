package eu.thesis.onlinecatchlog

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import eu.thesis.onlinecatchlog.screens.account.AccountView
import eu.thesis.onlinecatchlog.screens.catchlog.CatchLogView
import eu.thesis.onlinecatchlog.screens.catchlog.CatchLogViewModel
import eu.thesis.onlinecatchlog.screens.login.LoginView
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

    composable(SPLASH_SCREEN){
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(ACCOUNT_SCREEN){
        AccountView(restartApp = { route -> appState.clearAndNavigate(route) }, openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
    composable(CATCHLOG_SCREEN) {
        val viewModel: CatchLogViewModel = hiltViewModel()
        val state by viewModel.state.collectAsState()
        CatchLogView(state = state, onEvent = viewModel::onEvent, openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
    }
}
