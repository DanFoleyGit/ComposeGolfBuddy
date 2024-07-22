package composegolfbuddy.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "range_log")
data class RangeLog(
    @PrimaryKey @ColumnInfo("id") val id: String,
    @ColumnInfo("location") val location: String = "",
    @ColumnInfo("date")val date: String = "",
    @ColumnInfo("goal") val goal: String = "",
    @ColumnInfo("balls_hit") val ballsHit: String = "",
    @ColumnInfo("summary") val summary: String = ""
)
