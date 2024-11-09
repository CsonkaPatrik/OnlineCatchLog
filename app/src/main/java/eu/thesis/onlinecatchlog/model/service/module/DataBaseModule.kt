package eu.thesis.onlinecatchlog.model.service.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import eu.thesis.onlinecatchlog.screens.catchlog.CatchLogDao

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideCatchLogDatabase(@ApplicationContext context: Context): CatchLogDataBase {
        return Room.databaseBuilder(
            context,
            CatchLogDataBase::class.java,
            "catch_log_database"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideCatchLogDao(database: CatchLogDataBase): CatchLogDao {
        return database.dao
    }
}