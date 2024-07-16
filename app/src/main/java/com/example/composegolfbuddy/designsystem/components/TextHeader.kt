package com.example.composegolfbuddy.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextHeader(textValue: String) {
    Row(modifier = Modifier.padding(8.dp)) {
        Icon(imageVector = Icons.Filled.Info, contentDescription = "")
        Text(
            text = textValue,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, // Adjust size as needed
            ),
            modifier = Modifier.padding(start = 4.dp) // Add spacing below
        )
    }
}