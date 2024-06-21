package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RangeLogNoteWithScroll(value: String, size: Int) {
    Card(modifier = Modifier.heightIn(max = 150.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerLow // Set your desired background color here
        )
    ) {
        Text(
            text = value,
            style = TextStyle(
                fontSize = size.sp
            ),
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}