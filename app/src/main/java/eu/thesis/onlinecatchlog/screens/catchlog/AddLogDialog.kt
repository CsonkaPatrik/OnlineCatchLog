package eu.thesis.onlinecatchlog.screens.catchlog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import eu.thesis.onlinecatchlog.model.service.CatchLogState

@Composable
fun AddLogDialog(
    state: CatchLogState,
    onEvent: (CatchLogEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(CatchLogEvent.HideDialog)
        },
        title = { Text(text = "Fogás hozzáadása") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TextField(
                    value = state.lakeName,
                    onValueChange = {
                        onEvent(CatchLogEvent.setLakeName(it))
                    },
                    placeholder = {
                        Text(text = "Tó megnevezése")
                    }
                )
                TextField(
                    value = state.lakeCode.toString(),
                    onValueChange = {
                        val lakeCodeInt = it.toIntOrNull()
                        if (lakeCodeInt != null) {
                            onEvent(CatchLogEvent.setLakeCode(lakeCodeInt))
                        } else {
                            //TODO
                        }
                    },
                    placeholder = {
                        Text(text = "Víztérkód")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                TextField(
                    value = state.fishName,
                    onValueChange = {
                        onEvent(CatchLogEvent.setFishName(it))
                    },
                    placeholder = {
                        Text(text = "Hal megnevezése")
                    }
                )

                TextField(
                    value = state.fishWeight.toString(),
                    onValueChange = {
                        val fishWeightInt = it.toIntOrNull()
                        if (fishWeightInt != null) {
                            onEvent(CatchLogEvent.setFishWeight(fishWeightInt))
                        } else {
                            //TODO
                        }
                    },
                    placeholder = {
                        Text(text = "Hal súlya (kg)")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }
        },
        buttons = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd
            ) {
                Button(onClick = {
                    onEvent(CatchLogEvent.SaveCatchLog)
                }) {
                    Text(text = "Mentés")
                }
            }
        }
    )
}