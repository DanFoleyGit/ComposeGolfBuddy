package com.example.composegolfbuddy.screens.rangelogs

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RangeLogsViewModel @Inject constructor() : ViewModel() {

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
        rangeLocation = ""
        rangeDate = ""
        rangeGoal = ""
        rangeBallsHit = ""
        rangeSummary = ""
    }

}