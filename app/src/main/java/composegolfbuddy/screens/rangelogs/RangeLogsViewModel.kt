package composegolfbuddy.screens.rangelogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import composegolfbuddy.model.RangeLog
import composegolfbuddy.screens.createrangelog.RangeLogsUiState
import composegolfbuddy.usecases.AddRangeLogUseCase
import composegolfbuddy.usecases.DeleteRangeLogUseCase
import composegolfbuddy.usecases.GetAllRangeLogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RangeLogsViewModel @Inject constructor(
    private val addRangeLogUseCase: AddRangeLogUseCase,
    private val getAllRangeLogsUseCase: GetAllRangeLogsUseCase,
    private val deleteRangeLogUseCase: DeleteRangeLogUseCase
) : ViewModel() {

    private var _RangeLogsUiState = MutableStateFlow(RangeLogsUiState())
    var rangeLogsUiState: StateFlow<RangeLogsUiState> = _RangeLogsUiState.asStateFlow()

    var rangeLocation by mutableStateOf("")
        private set
    var rangeDate by mutableStateOf("")
        private set
    var rangeGoal by mutableStateOf("")
        private set
    var rangeBallsHit by mutableStateOf("")
        private set
    var rangeSummary by mutableStateOf("")
        private set

    fun updateRangeLocation(value: String) {
        rangeLocation = value
    }

    fun updateRangeDate(value: String) {
        rangeDate = value
    }

    fun updateRangeGoal(value: String) {
        rangeGoal = value
    }

    fun updateRangeBallsHit(value: String) {
        rangeBallsHit = value
    }

    fun updateRangeSummary(value: String) {
        rangeSummary = value
    }

    fun toggleSortByDate() {

        if(!_RangeLogsUiState.value.isSortedByDate) {
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

            _RangeLogsUiState.update { currentState ->
                val sortedList = currentState.rangeLogsList.sortedBy {
                    dateFormat.parse(it.date) ?: Date(0)
                }
                currentState.copy(
                    rangeLogsList = sortedList,
                    isSortedByDate = true,
                    isSortedByLocation = false,
                    isASC = false
                )
            }
        } else {
            populateRangeLogsList()
        }
    }

    fun toggleSortByLocation() {
        if(!_RangeLogsUiState.value.isSortedByLocation) {
            _RangeLogsUiState.update { currentState ->
                val sortedList = currentState.rangeLogsList.sortedBy { it.location }
                currentState.copy(
                    rangeLogsList = sortedList,
                    isSortedByDate = false,
                    isSortedByLocation = true,
                    isASC = false
                )
            }
        } else {
            populateRangeLogsList()
        }
    }

    fun toggleAscendingDescending() {
        _RangeLogsUiState.update { currentState ->
            val sortedList = currentState.rangeLogsList.reversed()
            currentState.copy(
                rangeLogsList = sortedList,
                isASC = !currentState.isASC
            )
        }
    }

    init {
        _RangeLogsUiState.value = RangeLogsUiState()
        clearAll()
        populateRangeLogsList()
    }

    private fun populateRangeLogsList() {
        viewModelScope.launch {
            _RangeLogsUiState.update { currentState ->
                val rangeLogsList = getAllRangeLogsUseCase.invoke().first()
                currentState.copy(
                    rangeLogsList = rangeLogsList,
                    displayHint = rangeLogsList.isEmpty(),
                    isSortedByDate = false,
                    isSortedByLocation = false,
                    isASC = false)
            }
        }
    }

    fun clearAll() {
        rangeLocation = ""
        rangeDate = ""
        rangeGoal = ""
        rangeBallsHit = ""
        rangeSummary = ""
    }

    fun processRangeLog() {
        if(validateDate()) {
            createRangeLog()
            clearAll()
        }
    }

    private fun validateDate(): Boolean {
        if (rangeDate.isNotEmpty()) {
            return true
        } else {
            return false
        }
    }

    fun deleteById(id: String) {
        viewModelScope.launch {
            deleteRangeLogUseCase.invoke(id)
            populateRangeLogsList()
        }
    }

    private fun createRangeLog() = viewModelScope.launch {
        addRangeLogUseCase.insertRangeLog(
            RangeLog(
                id = UUID.randomUUID().toString(),
                location = rangeLocation,
                date = rangeDate,
                goal = rangeGoal,
                ballsHit = rangeBallsHit,
                summary = rangeSummary
            )
        )
        populateRangeLogsList()
    }
}