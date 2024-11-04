package eu.thesis.onlinecatchlog.screens.login

import eu.thesis.onlinecatchlog.SIGN_IN_SCREEN

import eu.thesis.onlinecatchlog.model.service.AccountService
import eu.thesis.onlinecatchlog.screens.main.MainViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import eu.thesis.onlinecatchlog.MAIN_VIEW_SCREEN
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val accountService: AccountService
) : MainViewModel() {
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
            openAndPopUp(MAIN_VIEW_SCREEN, SIGN_IN_SCREEN)
        }
    }
}