package com.example.composegolfbuddy.screens.modifyclubsscreen

data class ModifyClubsStateUI(

    var errorState: Boolean = false,

    var clubTypeError: Boolean = false,
    var clubTypeIndex: Int = -1,
    var clubTypeErrorMessage: String = "",

    var clubBrandError: Boolean = false,
    var clubBrandErrorMessage: String = "",

    var clubLoftError: Boolean = false,
    var clubLoftErrorMessage: String = "",

    var distanceError: Boolean = false,
    var distanceErrorMessage: String = "",

    var showExtraDistances: Boolean = false,
)
