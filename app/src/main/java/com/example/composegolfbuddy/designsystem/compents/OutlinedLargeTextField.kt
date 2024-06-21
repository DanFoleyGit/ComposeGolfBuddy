package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue


@Composable
fun OutlinedLargeTextField(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    title: String = "",
    maxLength: Int,
) {
    var length by rememberSaveable { mutableStateOf(0) }
        OutlinedTextField(
            shape = MaterialTheme.shapes.large,
            value = initialValue,
            onValueChange = { newValue ->
                if(newValue.length <= maxLength) {
                    valueChanged(newValue)
                    length = newValue.length
                } else {
                    valueChanged(newValue.substring(0, maxLength))
                    length = maxLength
                }
            },
            label = { Text(title) },
            singleLine = false,
            minLines = 5,
            supportingText = { Text("$length/$maxLength")}
        )
}
