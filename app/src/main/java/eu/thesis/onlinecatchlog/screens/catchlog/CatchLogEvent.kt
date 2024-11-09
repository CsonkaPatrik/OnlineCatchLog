package eu.thesis.onlinecatchlog.screens.catchlog

import eu.thesis.onlinecatchlog.model.service.module.SortType


sealed interface CatchLogEvent {
    object SaveCatchLog: CatchLogEvent
    data class setLakeName(val lakeName: String): CatchLogEvent
    data class setLakeCode(val lakeCode: Int): CatchLogEvent
    data class setFishName(val fishName: String): CatchLogEvent
    data class setFishWeight(val fishWeight: Int): CatchLogEvent
    object ShowDialog: CatchLogEvent
    object HideDialog: CatchLogEvent
    data class SortLogs(val sortType: SortType): CatchLogEvent
}