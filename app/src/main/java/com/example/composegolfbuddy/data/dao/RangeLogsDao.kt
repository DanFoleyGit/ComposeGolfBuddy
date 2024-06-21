package com.example.composegolfbuddy.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.composegolfbuddy.model.RangeLog
import kotlinx.coroutines.flow.Flow

@Dao
interface RangeLogsDao {

    @Query("SELECT * FROM range_log")
    fun getAll(): Flow<List<RangeLog>>

    @Insert
    suspend fun insert(rangeLog: RangeLog)

    @Query("DELETE FROM range_log Where id = :id")
    suspend fun deleteById(id: String)

}
