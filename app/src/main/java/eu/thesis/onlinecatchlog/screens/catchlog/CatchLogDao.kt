package eu.thesis.onlinecatchlog.screens.catchlog

import androidx.room.*
import eu.thesis.onlinecatchlog.model.service.CatchLog
import kotlinx.coroutines.flow.Flow

@Dao
interface CatchLogDao {

    @Upsert
    fun upsertCatchLog(catchLog: CatchLog)

    @Query("SELECT * FROM catchlog ORDER BY catchTime DESC")
    fun getLogsOrderedByTime(): Flow<List<CatchLog>>
}