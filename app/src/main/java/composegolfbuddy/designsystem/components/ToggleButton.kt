package composegolfbuddy.designsystem.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToggleButton(
    toggleOn: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val rotationState by animateFloatAsState(
        targetValue = if (toggleOn) 180f else 0f
    )

    Card(
        modifier = Modifier.clip(RoundedCornerShape(100.dp))
            .background(color = MaterialTheme.colorScheme.primary)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ).then(modifier),
        onClick = {
            onClick()
        }
    ) {
        Icon(
            Icons.Filled.ArrowDropDown,
            contentDescription = null,
            modifier = modifier.rotate(rotationState)
                .background(color = MaterialTheme.colorScheme.primary)
            ,
            tint = Color.White,
        )
    }
}
