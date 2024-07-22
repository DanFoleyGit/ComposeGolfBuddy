package composegolfbuddy.screens.createrangelog

import composegolfbuddy.model.RangeLog

data class RangeLogsUiState(
    var rangeLogsList: List<RangeLog> = emptyList(),
    var displayHint: Boolean = false,
    var isSortedByDate: Boolean = false,
    var isSortedByLocation: Boolean = false,
    var isASC: Boolean = false
)
