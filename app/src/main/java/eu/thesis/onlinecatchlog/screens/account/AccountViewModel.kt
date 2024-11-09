package eu.thesis.onlinecatchlog.screens.account

import dagger.hilt.android.lifecycle.HiltViewModel
import eu.thesis.onlinecatchlog.ACCOUNT_SCREEN
import eu.thesis.onlinecatchlog.CATCHLOG_SCREEN
import eu.thesis.onlinecatchlog.SPLASH_SCREEN
import eu.thesis.onlinecatchlog.model.service.AccountService
import eu.thesis.onlinecatchlog.screens.MainAppViewModel
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountService: AccountService
) : MainAppViewModel() {
    fun initialize(restartApp: (String) -> Unit) {
        launchCatching {
            accountService.currentUser.collect { user ->
                if (user == null) restartApp(SPLASH_SCREEN)
            }
        }
    }

    fun onSignOutClick() {
        launchCatching {
            accountService.signOut()
        }
    }

    fun onCatchLogClick(openAndPopUp: (String, String) -> Unit){
        launchCatching {
            openAndPopUp(CATCHLOG_SCREEN, ACCOUNT_SCREEN)
        }
    }
}