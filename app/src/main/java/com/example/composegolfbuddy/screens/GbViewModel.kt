package com.example.composegolfbuddy.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composegolfbuddy.screens.homeScreen.HomeScreenUiState
import com.example.composegolfbuddy.screens.modifyclubsscreen.ModifyClubsStateUI
import com.example.composegolfbuddy.usecases.AddClubUseCase
import com.example.composegolfbuddy.usecases.GetClubTypesUseCase
import com.example.composegolfbuddy.usecases.GetClubsStaticUseCase
import com.example.composegolfbuddy.usecases.RetrieveClubByNameUseCase
import com.multiplatform.clubdistances.homeScreen.model.Club
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
    private val getClubTypesUseCase: GetClubTypesUseCase,
    private val retrieveClubByNameUseCase: RetrieveClubByNameUseCase,
    private val addClubUseCase: AddClubUseCase,
    private val getClubsStaticUseCase: GetClubsStaticUseCase
) : ViewModel() {

    // Home Screen UI state
    private var _homeScreenState = MutableStateFlow(HomeScreenUiState())
    var homeScreenState: StateFlow<HomeScreenUiState> = _homeScreenState.asStateFlow()

    // modify clubs screen
    private var _modifyClubsState = MutableStateFlow(ModifyClubsStateUI())
    var modifyClubsState: StateFlow<ModifyClubsStateUI> = _modifyClubsState.asStateFlow()

    // clubTypes
    var clubTypesList = getClubTypesUseCase.invoke()

    //modify clubs inputs
    var clubTypeInput by mutableStateOf("")
        private set
    var clubTypeIndex by mutableStateOf(-1)
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

    fun updateClubTypeIndex(value: Int) {
        clubTypeIndex = value
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

    /* methods called in home screen */
    private fun populateClubsData() {
        viewModelScope.launch {
            _homeScreenState.update { currentState ->
                currentState.copy(clubList = getClubsStaticUseCase.invoke().first())
            }
        }
    }

    /* methods called from ModifyClubsScreen */
    fun processClubInputs() {
        if (validateFields()) {
            clearErrorStates()
            insertClub()
            resetFields()
        }
    }

    fun retrieveSelectedClub() {
        viewModelScope.launch {
            val retrievedClub = retrieveClubByNameUseCase.invoke(clubTypeInput)
            if (retrievedClub != null) {
                updateClubBrandValue(retrievedClub.clubBrand)
                updateClubLoftValue(retrievedClub.clubLoft)
                updateClubDistanceValue(retrievedClub.distance.toString())
                clearErrorStates()
            } else {
                resetFields(false)
            }
        }
    }

    private fun clearErrorStates() {
        _modifyClubsState.update { it ->
            it.copy(
                errorState = false,
                clubTypeError = false,
                clubBrandError = false,
                clubLoftError = false,
                distanceError = false
            )
        }
    }

    private fun validateFields(): Boolean {
        val textPattern = Regex("^[a-zA-Z0-9]+$")
        val numPattern = Regex("^[0-9]+$")

        if (clubTypeInput.isEmpty() || !clubTypeInput.matches(textPattern)) {
            _modifyClubsState.update { it ->
                it.copy(
                    clubTypeError = true,
                    clubTypeErrorMessage = "Needs to be letter two letter code."
                )
            }
            return false
        } else {
            if (_modifyClubsState.value.clubTypeError) {
                _modifyClubsState.update { it ->
                    it.copy(clubTypeError = false)
                }
            }
        }

        if (clubBrandInput.isEmpty() || !clubBrandInput.matches(textPattern)) {
            _modifyClubsState.update { it ->
                it.copy(
                    clubBrandError = true,
                    clubBrandErrorMessage = "Can only contain letters."
                )
            }
            return false
        } else {
            if (_modifyClubsState.value.clubBrandError) {
                _modifyClubsState.update { it ->
                    it.copy(clubBrandError = false)
                }
            }
        }

        if (clubLoftInput.isEmpty() || !clubLoftInput.matches(numPattern)) {
            _modifyClubsState.update { it ->
                it.copy(
                    clubLoftError = true,
                    clubLoftErrorMessage = "Can only be numbers."
                )
            }
            return false
        } else {
            if (_modifyClubsState.value.clubLoftError) {
                _modifyClubsState.update { it ->
                    it.copy(clubLoftError = false)
                }
            }
        }

        if (distanceInput.isEmpty() || !distanceInput.matches(numPattern)) {
            _modifyClubsState.update { it ->
                it.copy(
                    distanceError = true,
                    distanceErrorMessage = "Can only be numbers."
                )
            }
            return false
        } else {
            if (_modifyClubsState.value.distanceError) {
                _modifyClubsState.update { it ->
                    it.copy(distanceError = false)
                }
            }
        }

        return true
    }

    private fun resetFields(resetIndex: Boolean = true) {
        updateClubBrandValue("")
        updateClubLoftValue("")
        updateClubDistanceValue("")
        if (resetIndex) {
            updateClubTypeValue("")
            updateClubTypeIndex(-1)
        }
    }

    private fun insertClub() = viewModelScope.launch {
        addClubUseCase.invoke(
            Club(
                clubTypeInput,
                clubBrandInput,
                clubLoftInput,
                distanceInput.toInt()
            )
        )
        populateClubsData()
    }

}
