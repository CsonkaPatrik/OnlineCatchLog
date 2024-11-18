package eu.thesis.onlinecatchlog.model.service

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CatchLog(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val catchTime: Long = System.currentTimeMillis(),
    val lakeName: String,
    val lakeCode: Int,
    val fishName: String,
    val fishWeight: Double
)