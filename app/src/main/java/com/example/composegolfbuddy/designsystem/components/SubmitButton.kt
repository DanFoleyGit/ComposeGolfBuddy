package com.example.composegolfbuddy.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SubmitButton(modifier: Modifier = Modifier, processClubInputs: () -> Unit) {
    Button(onClick = {
        processClubInputs()
    }) {
        modifier.padding(8.dp)
        Text(text = "Add Club")
    }
}