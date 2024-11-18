package eu.thesis.onlinecatchlog.screens.catchlog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eu.thesis.onlinecatchlog.ACCOUNT_SCREEN
import eu.thesis.onlinecatchlog.CATCHLOG_SCREEN
import eu.thesis.onlinecatchlog.SIGN_IN_SCREEN
import eu.thesis.onlinecatchlog.model.service.CatchLog
import eu.thesis.onlinecatchlog.model.service.CatchLogState
import eu.thesis.onlinecatchlog.model.service.module.SortType
import eu.thesis.onlinecatchlog.screens.MainAppViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CatchLogViewModel @Inject constructor(
    private val dao: CatchLogDao
): MainAppViewModel() {

    fun onAccountClick(openAndPopUp: (String, String) -> Unit) {
        launchCatching {
            openAndPopUp(ACCOUNT_SCREEN, CATCHLOG_SCREEN)
        }
    }

    private val _sortType = MutableStateFlow(SortType.TIME)
    private val _catchLogs = _sortType
        .flatMapLatest { sortType ->
            when(sortType) {
                SortType.TIME -> dao.getLogsOrderedByTime()
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    private val _state = MutableStateFlow(CatchLogState())
    val state = combine(_state, _sortType, _catchLogs) { state, sortType, catchLogs ->
        state.copy(
            catchLogs = catchLogs,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CatchLogState())

    fun onEvent(event: CatchLogEvent) {
        when(event) {
            CatchLogEvent.HideDialog -> {
                _state.update { it.copy(
                    isAddingCatchLog = false
                ) }
            }
            CatchLogEvent.SaveCatchLog -> {
                val lakeName = state.value.lakeName
                val lakeCode = state.value.lakeCode
                val fishName = state.value.fishName
                val fishWeight = state.value.fishWeight

                if(lakeName.isBlank() || fishName.isBlank()) {
                    return
                }

                val catchLog = CatchLog(
                    lakeName = lakeName,
                    lakeCode = lakeCode,
                    fishName = fishName,
                    fishWeight = fishWeight
                )
                viewModelScope.launch {
                    dao.upsertCatchLog(catchLog)
                }
                _state.update { it.copy(
                    isAddingCatchLog = false,
                    lakeName = "",
                    lakeCode = 0,
                    fishName = "",
                    fishWeight = 0.0
                ) }
            }
            is CatchLogEvent.setLakeName -> {
                _state.update { it.copy(
                    lakeName = event.lakeName
                ) }
            }
            is CatchLogEvent.setLakeCode -> {
                _state.update { it.copy(
                    lakeCode = event.lakeCode
                ) }
            }
            is CatchLogEvent.setFishName -> {
                _state.update { it.copy(
                    fishName = event.fishName
                ) }
            }
            is CatchLogEvent.setFishWeight -> {
                _state.update { it.copy(
                    fishWeight = event.fishWeight
                ) }
            }
            CatchLogEvent.ShowDialog -> {
                _state.update { it.copy(
                    isAddingCatchLog = true
                ) }
            }
            is CatchLogEvent.SortLogs -> {
                _sortType.value = event.sortType
            }
        }
    }
}