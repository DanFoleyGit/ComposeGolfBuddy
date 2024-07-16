package composegolfbuddy.repositories

import composegolfbuddy.model.RangeLog
import kotlinx.coroutines.flow.Flow

interface RangeLogsRepository {

    suspend fun insertRangeLog(rangeLog: RangeLog)

    fun getAllRangeLogs(): Flow<List<RangeLog>>

    suspend fun deleteById(id: String)
}