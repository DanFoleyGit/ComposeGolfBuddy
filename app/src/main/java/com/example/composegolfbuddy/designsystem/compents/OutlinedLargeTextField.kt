package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun OutlinedLargeTextField(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    title: String = "",
    maxLength: Int,
) {
        OutlinedTextField(
//            modifier = Modifier.height(500.dp),
            shape = MaterialTheme.shapes.large,
            value = initialValue,
            onValueChange = { newValue ->
                if (!newValue.endsWith(' ')) {
                    if (newValue.length <= maxLength) {
                        valueChanged(newValue)
                    }
                }
            },
            label = { Text(title) },
            singleLine = false,
            minLines = 5
        )
}
