package eu.thesis.onlinecatchlog.screens.login

import eu.thesis.onlinecatchlog.SIGN_IN_SCREEN

import eu.thesis.onlinecatchlog.model.service.AccountService
import eu.thesis.onlinecatchlog.screens.MainAppViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.thesis.onlinecatchlog.ACCOUNT_SCREEN
import eu.thesis.onlinecatchlog.CATCHLOG_SCREEN
import eu.thesis.onlinecatchlog.MAIN_VIEW_SCREEN
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : MainAppViewModel() {
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")

    fun updateEmail(newEmail: String) {
        email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        password.value = newPassword
    }

    fun onSignInClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            accountService.signIn(email.value, password.value)
            openAndPopUp(CATCHLOG_SCREEN, SIGN_IN_SCREEN)
        }
    }

}