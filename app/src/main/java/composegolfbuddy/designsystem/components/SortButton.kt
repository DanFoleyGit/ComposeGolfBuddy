package composegolfbuddy.designsystem.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SortButton(
    trueText: String,
    falseText: String,
    onClick: () -> Unit,
    isSortActive: Boolean,
    icon: ImageVector? = null
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(
                if (isSortActive) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
    ){
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (isSortActive) trueText else falseText,
                color = MaterialTheme.colorScheme.onPrimary,
            )
            if (icon != null) {
                Icon(
                    icon,
                    "description",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(15.dp)
                        .rotate(if (isSortActive) 0f else 180f)
                )
            }
        }
    }
}