package com.example.composegolfbuddy.designsystem.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ElevatedCreateButton() {
    ElevatedButton(onClick = { /*TODO*/ }) {
        Icon(
            Icons.Filled.Create,
            "description",
            modifier = Modifier
                .padding(4.dp)
                .size(32.dp)
        )
        Text(text = "Create")
    }
}