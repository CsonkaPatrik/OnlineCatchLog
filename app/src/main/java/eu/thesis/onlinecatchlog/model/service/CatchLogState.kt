package eu.thesis.onlinecatchlog.model.service

import eu.thesis.onlinecatchlog.model.service.module.SortType

data class CatchLogState(
    val catchLogs: List<CatchLog> = emptyList(),
    val catchTime: Long = System.currentTimeMillis(),
    val lakeName: String = "",
    val lakeCode: Int = 0,
    val fishName: String = "",
    val fishWeight: Double = 0.0,
    val sortType: SortType = SortType.TIME,

    val isAddingCatchLog: Boolean = false
)
