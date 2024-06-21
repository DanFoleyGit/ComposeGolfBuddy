package com.example.composegolfbuddy.screens.modifyclubsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.composegolfbuddy.designsystem.compents.OutlinedTextFieldForInputs
import com.example.composegolfbuddy.designsystem.compents.OutlinedTextFieldWithDropdown
import com.example.composegolfbuddy.designsystem.compents.SubmitButton
import com.example.composegolfbuddy.screens.GbViewModel

@Composable
fun ModifyClubsScreen(viewModel: GbViewModel, modifier: Modifier = Modifier) {

    val state = viewModel.modifyClubsState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedTextFieldWithDropdown(
            valueChanged = { viewModel.updateClubTypeValue(it) },
            indexChanged = { viewModel.updateClubTypeIndex(it) },
            retrieveClub = { viewModel.retrieveSelectedClub() },
            selectedIndex = viewModel.clubTypeIndex,
            isErrorState = state.value.clubTypeError,
            errorMessage = state.value.clubTypeErrorMessage,
            title = "Type",
            options = viewModel.clubTypesList
        )

        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubBrandInput,
            valueChanged = { viewModel.updateClubBrandValue(it) },
            isErrorState = state.value.clubBrandError,
            errorMessage = state.value.clubBrandErrorMessage,
            title = "Brand",
            maxLength = 16,
            modifier = Modifier.weight(1f)
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubLoftInput,
            valueChanged = { viewModel.updateClubLoftValue(it) },
            isErrorState = state.value.clubLoftError,
            errorMessage = state.value.clubLoftErrorMessage,
            title = "Club Loft",
            maxLength = 12,
            modifier = Modifier.weight(1f)
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.distanceInput,
            valueChanged = { viewModel.updateClubDistanceValue(it) },
            isErrorState = state.value.distanceError,
            errorMessage = state.value.distanceErrorMessage,
            title = "Distance",
            maxLength = 3,
            modifier = Modifier.weight(1f)
        )

        SubmitButton(processClubInputs = viewModel::processClubInputs)
    }
}


//@Preview(showBackground = true)
//@Composable
//fun ModifyClubsScreenPreview() {
//    ModifyClubsScreen(viewModel = GbViewModel())
//}