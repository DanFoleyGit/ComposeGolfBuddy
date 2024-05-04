package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


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

//@Preview
//@Composable
//fun PreviewOutlinedTextFieldForInputs() {
//    OutlinedTextFieldForInputs(
//        initialValue = "",
//        valueChanged = viewModel.,
//        isErrorState = false,
//        errorMessage =,"errorMessage",
//        title = "Distance",
//        maxLength = 3
//    )
//}
