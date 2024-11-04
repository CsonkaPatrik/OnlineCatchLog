package eu.thesis.onlinecatchlog

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
//Classes for specifying the drawer elements
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
            object  Account: DrawerScreen(
                "Fiók",
                "account",
                R.drawable.ic_account
            )

            object CatchLog: DrawerScreen(
                "Fogási napló",
                "add_account",
                R.drawable.catch_log
            )

            object Settings: DrawerScreen(
                "Beállítások",
                "settings",
                R.drawable.ic_settings
            )
        }



}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.CatchLog,
    Screen.DrawerScreen.Settings
)