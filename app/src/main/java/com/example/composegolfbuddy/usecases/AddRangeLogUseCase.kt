package com.example.composegolfbuddy.usecases

import com.example.composegolfbuddy.model.RangeLog
import com.example.composegolfbuddy.repositories.RangeLogsRepository
import javax.inject.Inject

class AddRangeLogUseCase @Inject constructor(
    private val rangeLogsRepository: RangeLogsRepository
) {

    suspend fun insertRangeLog(rangeLog: RangeLog) {
        rangeLogsRepository.insertRangeLog(rangeLog)
    }
}