package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun SpeechBox(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(10.dp))
            .padding(16.dp)
    ) {
        Text(text = text)
        Triangle(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (0).dp, y = 35.dp)
        )
    }
}
@Composable
fun Triangle(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.secondaryContainer
    Canvas(
        modifier = modifier
        .size(20.dp)
        .background(MaterialTheme.colorScheme.background,)
    ) {
        drawPath(
            path = Path().apply {
                moveTo(size.width / 2f, size.height) // Bottom tip
                lineTo(0f, 0f) // Top left
                lineTo(size.width, 0f) // Top right
                close()
            },
            color = color
        )
    }
}