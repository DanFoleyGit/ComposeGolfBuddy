package com.example.composegolfbuddy.designsystem.compents

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.shape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RangeLogNoteWithScroll(value: String, size: Int) {
    Card(modifier = Modifier,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.inverseSurface)
//        shape =
    ) {

        Text(
            text = value,
            style = TextStyle(
                fontSize = size.sp
            ),
            modifier = Modifier
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
        )
    }
}