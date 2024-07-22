package composegolfbuddy.usecases

import composegolfbuddy.repositories.RangeLogsRepository
import javax.inject.Inject

class DeleteRangeLogUseCase @Inject constructor(
    private val rangeLogsRepository: RangeLogsRepository
) {

    suspend fun invoke(id: String) {
        rangeLogsRepository.deleteById(id)
    }
}