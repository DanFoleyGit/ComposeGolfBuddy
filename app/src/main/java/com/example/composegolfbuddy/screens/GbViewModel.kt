package com.example.composegolfbuddy.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegolfbuddy.screens.homeScreen.HomeScreenUiState
import com.example.composegolfbuddy.screens.modifyclubsscreen.ModifyClubsStateUI
import com.example.composegolfbuddy.usecases.GetClubsStaticUseCase
import com.multiplatform.clubdistances.homeScreen.model.Club
import com.multiplatform.clubdistances.homeScreen.useCases.AddClubUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GbViewModel @Inject constructor(
    private val addClubUseCase: AddClubUseCase,
    private val getClubsStaticUseCase: GetClubsStaticUseCase
) : ViewModel() {

    // Home Screen UI state
    private var _homeScreenState = MutableStateFlow(HomeScreenUiState())
    var homeScreenState: StateFlow<HomeScreenUiState> = _homeScreenState.asStateFlow()

    // modify clubs screen
    private var _modifyClubsState = MutableStateFlow(ModifyClubsStateUI())
    var modifyClubsState: StateFlow<ModifyClubsStateUI> = _modifyClubsState.asStateFlow()

    //modify clubs inputs
    var clubTypeInput by mutableStateOf("")
        private set

    var clubBrandInput by mutableStateOf("")
        private set

    var clubLoftInput by mutableStateOf("")
        private set

    var distanceInput by mutableStateOf("")
        private set

    init {
        _homeScreenState.value = HomeScreenUiState()
        _modifyClubsState.value = ModifyClubsStateUI(false)
        populateClubsData()
    }

    fun updateClubTypeValue(value: String) {
        clubTypeInput = value
    }

    fun updateClubBrandValue(value: String) {
        clubBrandInput = value
    }

    fun updateClubLoftValue(value: String) {
        clubLoftInput = value
    }

    fun updateClubDistanceValue(value: String) {
        distanceInput = value
    }

    private fun populateClubsData() {
        viewModelScope.launch {
            _homeScreenState.update { currentState ->
                currentState.copy(clubList = getClubsStaticUseCase.invoke().first())
            }
        }
    }

    fun processClubInputs() {

        /* TODO create validator class for each field */

        if (clubTypeInput.isEmpty() ||
            clubBrandInput.isEmpty() ||
            clubLoftInput.isEmpty() ||
            distanceInput.isEmpty()
        ) {
            _modifyClubsState.update { it ->
                it.copy(errorState = true)
            }
        } else {
            _modifyClubsState.update { it ->
                it.copy(errorState = false)
            }
            insert()
            resetFields()
            populateClubsData()
        }
    }

    private fun resetFields() {
        updateClubTypeValue("")
        updateClubBrandValue("")
        updateClubLoftValue("")
        updateClubDistanceValue("")
    }

    private fun insert() = viewModelScope.launch {
        addClubUseCase.invoke(
            Club(
                clubTypeInput,
                clubBrandInput,
                clubLoftInput,
                distanceInput.toInt()
            )
        )
    }

}
