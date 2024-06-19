package com.example.composegolfbuddy.screens.createrangelog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composegolfbuddy.designsystem.compents.MyDatePickerDialog
import com.example.composegolfbuddy.designsystem.compents.OutlinedLargeTextField
import com.example.composegolfbuddy.designsystem.compents.OutlinedTextFieldForInputs
import com.example.composegolfbuddy.screens.rangelogs.RangeLogsViewModel

@Composable
fun CreateRangeLogScreen(
    viewModel: RangeLogsViewModel,
    modifier: Modifier
) {

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text("How was your session?\n" +
                "- Any new feels you want to remember?\n" +
                "- Mention what went right and what can be worked on")

        Spacer(modifier = modifier.height(16.dp))

        MyDatePickerDialog { viewModel.updateRangeDate(it) }
        
        Spacer(modifier = modifier.height(16.dp))


        OutlinedTextFieldForInputs(
            initialValue = viewModel.rangeLocation,
            valueChanged = { viewModel.updateRangeLocation(it) },
            isErrorState = false,
            errorMessage = "",
            title = "Location",
            maxLength = 20,
            modifier = Modifier.weight(1f)
        )
        
        OutlinedTextFieldForInputs(
            initialValue = viewModel.rangeBallsHit,
            valueChanged = { viewModel.updateRangeBallsHit(it) },
            isErrorState = false,
            errorMessage = "",
            title = "Balls hit",
            maxLength = 3
        )

        OutlinedTextFieldForInputs(
            initialValue = viewModel.rangeGoal,
            valueChanged = { viewModel.updateRangeGoal(it) },
            isErrorState = false,
            errorMessage = "",
            title = "Range Goal",
            maxLength = 40,
            modifier = Modifier.weight(1f)
        )

        OutlinedLargeTextField(
            initialValue = viewModel.rangeSummary,
            valueChanged = { viewModel.updateRangeSummary(it)},
            maxLength = 300,
            title = "Summary"
        )
    }
}
