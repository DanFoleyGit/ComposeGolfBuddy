package composegolfbuddy.screens.homeScreen

import composegolfbuddy.model.Club

data class HomeScreenUiState(
    var clubList: List<Club> = emptyList<Club>(),
    var showInformationField: Boolean = false,
)
