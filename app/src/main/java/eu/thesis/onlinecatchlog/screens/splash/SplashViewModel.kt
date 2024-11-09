package eu.thesis.onlinecatchlog.screens.splash

import dagger.hilt.android.lifecycle.HiltViewModel
import eu.thesis.onlinecatchlog.CATCHLOG_SCREEN
import eu.thesis.onlinecatchlog.MAIN_VIEW_SCREEN
import eu.thesis.onlinecatchlog.SIGN_IN_SCREEN
import eu.thesis.onlinecatchlog.SPLASH_SCREEN
import eu.thesis.onlinecatchlog.model.service.AccountService
import eu.thesis.onlinecatchlog.screens.MainAppViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : MainAppViewModel() {

    fun onAppStart(openAndPopUp: (String, String) -> Unit) {
        if (accountService.hasUser()) openAndPopUp(CATCHLOG_SCREEN, SPLASH_SCREEN)
        else openAndPopUp(SIGN_IN_SCREEN, SPLASH_SCREEN)
    }
}
