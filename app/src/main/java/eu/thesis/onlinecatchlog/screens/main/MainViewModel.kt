package eu.thesis.onlinecatchlog.screens.main

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.thesis.onlinecatchlog.Screen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

open class MainViewModel:ViewModel() {

    private val _currentScreen: MutableState<Screen> = mutableStateOf(Screen.DrawerScreen.AddAccount)

    val currentScreen: MutableState<Screen>
        get() = _currentScreen

    fun setCurrentScreen(screen: Screen){
        _currentScreen.value = screen
    }

}