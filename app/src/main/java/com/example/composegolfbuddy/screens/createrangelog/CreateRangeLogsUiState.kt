package com.example.composegolfbuddy.screens.createrangelog

import com.example.composegolfbuddy.model.RangeLog

data class CreateRangeLogsUiState(
    var rangeLogsList: List<RangeLog> = emptyList(),
    var displayHint: Boolean = false
)
