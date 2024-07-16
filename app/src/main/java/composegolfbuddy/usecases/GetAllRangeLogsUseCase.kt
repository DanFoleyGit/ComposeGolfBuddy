package composegolfbuddy.usecases

import composegolfbuddy.model.RangeLog
import composegolfbuddy.repositories.RangeLogsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

class GetAllRangeLogsUseCase @Inject constructor(
    private val rangeLogsRepository: RangeLogsRepository
) {
    fun invoke() : Flow<List<RangeLog>> {
        return sortRangeLogsByDate(rangeLogsRepository.getAllRangeLogs())
    }

    private fun sortRangeLogsByDate(rangeLogsFlow: Flow<List<RangeLog>>): Flow<List<RangeLog>> {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return rangeLogsFlow.map { rangeLogs ->
            rangeLogs.sortedByDescending {
                dateFormat.parse(it.date)
            }
        }
    }
}
