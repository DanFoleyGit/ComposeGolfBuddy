package com.example.composegolfbuddy.screens.modifyclubsscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
            maxLength = 16
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.clubLoftInput,
            valueChanged = { viewModel.updateClubLoftValue(it) },
            isErrorState = state.value.clubLoftError,
            errorMessage = state.value.clubLoftErrorMessage,
            title = "Club Loft",
            maxLength = 12
        )
        OutlinedTextFieldForInputs(
            initialValue = viewModel.distanceInput,
            valueChanged = { viewModel.updateClubDistanceValue(it) },
            isErrorState = state.value.distanceError,
            errorMessage = state.value.distanceErrorMessage,
            title = "Distance",
            maxLength = 3
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
    errorMessage: String,
    title: String = "",
    maxLength: Int
) {
    Column(horizontalAlignment = Alignment.Start) {
        OutlinedTextField(
            value = initialValue,
            onValueChange = { newValue ->
                if (!newValue.endsWith(' ')) {
                    if (newValue.length <= maxLength) {
                        valueChanged(newValue)
                    }
                }
            },
            label = { Text(title) },
            isError = isErrorState,
            supportingText = {
                if (isErrorState) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            singleLine = true
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithDropdown(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    retrieveClub: () -> Unit,
    indexChanged: (Int) -> Unit,
    selectedIndex: Int,
    isErrorState: Boolean = false,
    errorMessage: String,
    title: String = "",
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.Start) {
        Box(modifier = Modifier.clickable { expanded = true }) {
            OutlinedTextField(
                value = if (selectedIndex != -1) options[selectedIndex] else initialValue,
                onValueChange = {
                    valueChanged(it)
                },
                label = { Text(title) },
                isError = isErrorState,
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = true }
                    )
                }
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        valueChanged(option)
                        retrieveClub()
                        indexChanged(index)
                        expanded = false
                    }
                )
            }
        }

        if (isErrorState) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
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