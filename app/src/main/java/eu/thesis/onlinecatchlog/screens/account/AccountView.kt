package eu.thesis.onlinecatchlog.screens.account

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import eu.thesis.onlinecatchlog.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountView(
    openAndPopUp: (String, String) -> Unit,
    restartApp: (String) -> Unit,
    viewModel: AccountViewModel = hiltViewModel()
){
    LaunchedEffect(Unit) { viewModel.initialize(restartApp) }

    var showExitAppDialog by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TopAppBar(
            title = { Text(stringResource(R.string.app_name)) },
            actions = {
                IconButton(onClick = { viewModel.onCatchLogClick(openAndPopUp) }) {
                    Icon(Icons.Filled.ArrowBack, "Catch log")
                }
            }
        )

        Button(onClick = { showExitAppDialog = true }) {
            Text(text = stringResource(R.string.sign_out),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(0.dp, 6.dp)
            )
        }


        if (showExitAppDialog) {
            AlertDialog(
                title = { Text(stringResource(R.string.sign_out_title)) },
                text = { Text(stringResource(R.string.sign_out_description)) },
                dismissButton = {
                    Button(onClick = { showExitAppDialog = false }) {
                        Text(text = stringResource(R.string.cancel))
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.onSignOutClick()
                        showExitAppDialog = false
                    }) {
                        Text(text = stringResource(R.string.sign_out))
                    }
                },
                onDismissRequest = { showExitAppDialog = false }
            )
        }

    }
}
