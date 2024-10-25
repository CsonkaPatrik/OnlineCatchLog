package eu.thesis.onlinecatchlog

import androidx.annotation.DrawableRes

sealed class Screen(val title: String, val route: String) {
//Classes for specifying the drawer elements
    sealed class DrawerScreen(val dTitle: String, val dRoute: String, @DrawableRes val icon: Int)
        : Screen(dTitle, dRoute){
            object  Account: DrawerScreen(
                "Account",
                "account",
                R.drawable.ic_account
            )

            object Subscription: DrawerScreen(
                "Settings",
                "settings",
                R.drawable.ic_settings
            )

            object AddAccount: DrawerScreen(
                "add Account",
                "add_account",
                R.drawable.ic_add_account
            )
        }



}

val screensInDrawer = listOf(
    Screen.DrawerScreen.Account,
    Screen.DrawerScreen.Subscription,
    Screen.DrawerScreen.AddAccount
)