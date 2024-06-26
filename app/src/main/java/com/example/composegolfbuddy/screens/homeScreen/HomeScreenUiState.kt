package com.example.composegolfbuddy.screens.homeScreen

import com.example.composegolfbuddy.model.Club

data class HomeScreenUiState(
    var clubList: List<Club> = emptyList<Club>(),
    var showInformationField: Boolean = false,
)
