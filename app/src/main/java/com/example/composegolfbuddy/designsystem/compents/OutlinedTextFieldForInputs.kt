package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun OutlinedTextFieldForInputs(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    isErrorState: Boolean = false,
    errorMessage: String,
    title: String = "",
    maxLength: Int,
    shouldAllowSpace: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = initialValue,
        onValueChange = { newValue ->
            if (!shouldAllowSpace && !newValue.endsWith(' ')) {
                if (newValue.length <= maxLength) {
                    valueChanged(newValue)
                }
            } else {
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

