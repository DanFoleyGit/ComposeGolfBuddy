package composegolfbuddy.screens.createrangelog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composegolfbuddy.designsystem.components.ActionButton
import composegolfbuddy.designsystem.components.MyDatePickerDialog
import composegolfbuddy.designsystem.components.OutlinedLargeTextField
import composegolfbuddy.designsystem.components.OutlinedTextFieldForInputs
import composegolfbuddy.screens.rangelogs.RangeLogsViewModel

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

        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(4.dp)
        ) {
            Text(
                text = "How was your session?",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, // Adjust size as needed
                ),
                modifier = Modifier.padding(bottom = 8.dp) // Add spacing below
            )

            Text(text = "- Any new feels you want to remember?")
            Text(text = "- Mention what went right and what can be worked on.")
        }


        Spacer(modifier = modifier.height(16.dp))

        MyDatePickerDialog(
            modifier = Modifier,
            initialDate = viewModel.rangeDate, // Pass the rangeDate value
            updateDate = { viewModel.updateRangeDate(it) }
        )

        Spacer(modifier = modifier.height(16.dp))


        OutlinedTextFieldForInputs(
            initialValue = viewModel.rangeLocation,
            valueChanged = { viewModel.updateRangeLocation(it) },
            isErrorState = false,
            errorMessage = "",
            title = "Location",
            maxLength = 20,
            shouldAllowSpace = true,
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
            shouldAllowSpace = true,
            modifier = Modifier.weight(1f)
        )

        OutlinedLargeTextField(
            initialValue = viewModel.rangeSummary,
            valueChanged = { viewModel.updateRangeSummary(it)},
            maxLength = 300,
            title = "Summary"
        )
        Row(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)
        ) {
            ActionButton(
                doAction =  { viewModel.clearAll() },
                icon = Icons.Filled.Clear,
                textValue = "Clear"
            )

            VerticalDivider(modifier.padding(8.dp))

            ActionButton(
                doAction =  { viewModel.processRangeLog() },
                icon = Icons.Filled.Create,
                textValue = "Create"
            )
        }
    }
}


