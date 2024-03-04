package com.example.composegolfbuddy.screens.modifyclubsscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composegolfbuddy.screens.GbViewModel

@Composable
fun ModifyClubsScreen(viewModel: GbViewModel, modifier: Modifier = Modifier) {

    val state = viewModel.modifyClubsState.collectAsState()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubTypeInput,
            valueChanged = { viewModel.updateClubTypeValue(it) },
            isErrorState = state.value.errorState,
            title = "Type"
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubBrandInput,
            valueChanged = { viewModel.updateClubBrandValue(it) },
            isErrorState = state.value.errorState,
            title = "Brand"
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubLoftInput,
            valueChanged = { viewModel.updateClubLoftValue(it) },
            isErrorState = state.value.errorState,
            title = "Club Loft"
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.distanceInput,
            valueChanged = { viewModel.updateClubDistanceValue(it) },
            isErrorState = state.value.errorState,
            title = "Distance"
        )

        SubmitButton(processClubInputs = viewModel::processClubInputs)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldForInputs(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    isErrorState: Boolean = false,
    title: String = ""
) {

    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = initialValue,
            onValueChange = { newValue -> valueChanged(newValue) },
            label = { Text(title) },
            isError = isErrorState,
            singleLine = true
        )
    }
}

@Composable
fun SubmitButton(modifier: Modifier = Modifier, processClubInputs: () -> Unit) {
    Button(onClick = {
        processClubInputs()
    }) {
        modifier.padding(8.dp)
        Text(text = "Add Club")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ModifyClubsScreenPreview() {
//    ModifyClubsScreen(viewModel = GbViewModel())
//}