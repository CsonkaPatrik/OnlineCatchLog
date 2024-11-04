package eu.thesis.onlinecatchlog.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.thesis.onlinecatchlog.Screen
import eu.thesis.onlinecatchlog.screensInDrawer
import eu.thesis.onlinecatchlog.screens.account.AccountView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()

    val viewModel: MainViewModel = viewModel()
    //Current view state
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember{
        viewModel.currentScreen.value
    }

    val title = remember{
        mutableStateOf(currentScreen.title)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title.value) },
                    navigationIcon = { IconButton(onClick = {
                        // Open drawer
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = "Menu")
                    }}
                )
        },scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp)) {
                items(screensInDrawer){
                    item -> 
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch{
                            scaffoldState.drawerState.close()
                        }
                        if(item.dRoute == "add_account"){
                            //open
                        }else{
                            controller.navigate(item.dRoute)
                            title.value = item.dTitle
                        }
                    }
                }
            }
        }



    ) {
        Navigation(navController = controller, viewModel = viewModel, pd = it)
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemCLicked : () -> Unit
){
    val background = if (selected) Color.DarkGray else Color.White
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemCLicked()
            }) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTitle,
            Modifier.padding(end = 8.dp, top = 4.dp)
        )
        Text(
            text = item.dTitle,
            style = MaterialTheme.typography.h5
        )
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd:PaddingValues){
    NavHost(navController = navController as NavHostController, startDestination = Screen.DrawerScreen.CatchLog.route, modifier = Modifier.padding(pd))
    {
        composable(Screen.DrawerScreen.CatchLog.route){
        }
        composable(Screen.DrawerScreen.Settings.route){

        }
        composable(Screen.DrawerScreen.Account.route){

        }
    }
}