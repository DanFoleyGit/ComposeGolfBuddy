package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToggleButton(
    toggleOn: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .clickable {
                onClick()
            }
            .then(modifier)
    ) {
        if (toggleOn) {
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = null,
                modifier = modifier, // size set by passed modifier
                tint = Color.White
            )
        } else {
            Icon(
                Icons.Filled.ArrowDropUp,
                contentDescription = null,
                modifier = modifier, // size set by passed modifier
                tint = Color.White
            )
        }
    }
}
