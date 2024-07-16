package composegolfbuddy.usecases

import composegolfbuddy.model.RangeLog
import composegolfbuddy.repositories.RangeLogsRepository
import javax.inject.Inject

class AddRangeLogUseCase @Inject constructor(
    private val rangeLogsRepository: RangeLogsRepository
) {

    suspend fun insertRangeLog(rangeLog: RangeLog) {
        rangeLogsRepository.insertRangeLog(rangeLog)
    }
}