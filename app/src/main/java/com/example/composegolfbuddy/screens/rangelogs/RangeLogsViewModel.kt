package com.example.composegolfbuddy.screens.rangelogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegolfbuddy.model.RangeLog
import com.example.composegolfbuddy.usecases.AddRangeLogUseCase
import com.example.composegolfbuddy.usecases.GetAllRangeLogsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class RangeLogsViewModel @Inject constructor(
    private val addRangeLogUseCase: AddRangeLogUseCase,
    private val getAllRangeLogsUseCase: GetAllRangeLogsUseCase
) : ViewModel() {

    private var _createRangeLogsUiState = MutableStateFlow(CreateRangeLogsUiState())
    var createRangeLogsUiState: StateFlow<CreateRangeLogsUiState> = _createRangeLogsUiState.asStateFlow()

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

    init {
        _createRangeLogsUiState.value = CreateRangeLogsUiState()
        clearAll()
        populateRangeLogsList()
    }

    private fun populateRangeLogsList() {
        viewModelScope.launch {
            _createRangeLogsUiState.update {currentState ->
                currentState.copy(rangeLogsList = getAllRangeLogsUseCase.invoke().first())
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