package composegolfbuddy.repositories

import composegolfbuddy.data.dao.RangeLogsDao
import composegolfbuddy.model.RangeLog
import kotlinx.coroutines.flow.Flow

class RangeLogsRepositoryImpl(private val rangeLogsDao: RangeLogsDao): RangeLogsRepository {
    override suspend fun insertRangeLog(rangeLog: RangeLog) {
        rangeLogsDao.insert(rangeLog)
    }

    override fun getAllRangeLogs(): Flow<List<RangeLog>> {
        return rangeLogsDao.getAll()
    }

    override suspend fun deleteById(id: String) {
        rangeLogsDao.deleteById(id)    }

}