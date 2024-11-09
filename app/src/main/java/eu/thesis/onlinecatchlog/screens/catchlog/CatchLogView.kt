package eu.thesis.onlinecatchlog.screens.catchlog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import eu.thesis.onlinecatchlog.R
import eu.thesis.onlinecatchlog.model.service.CatchLogState

@Composable
fun CatchLogView(
    openAndPopUp: (String, String) -> Unit,
    state: CatchLogState,
    onEvent: (CatchLogEvent) -> Unit,
    viewModel: CatchLogViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                actions = {
                    IconButton(onClick = { viewModel.onAccountClick(openAndPopUp) }) {
                        Icon(Icons.Filled.AccountCircle, "Account")
                    }
                }
            )
        },

        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(CatchLogEvent.ShowDialog)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Fogás"
                )
            }
        }
    ) {_ ->
        if(state.isAddingCatchLog) {
            AddLogDialog(state = state, onEvent = onEvent)
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(state.catchLogs) { catchLog ->
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = "${catchLog.fishName} ${catchLog.fishWeight}",
                            fontSize = 20.sp
                        )
                        Text(text = catchLog.catchTime.toString(), fontSize = 12.sp)
                        Text(text = catchLog.lakeName, fontSize = 12.sp)
                        Text(text = catchLog.lakeCode.toString(), fontSize = 6.sp)
                    }
                }
            }
        }
    }
}