package eu.thesis.onlinecatchlog.model.service.module

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.thesis.onlinecatchlog.model.service.CatchLog
import eu.thesis.onlinecatchlog.screens.catchlog.CatchLogDao

@Database(
    entities = [CatchLog::class],
    version = 1
)
abstract class CatchLogDataBase: RoomDatabase() {

    abstract val dao: CatchLogDao
}