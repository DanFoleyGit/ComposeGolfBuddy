package composegolfbuddy.designsystem.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun BoldTextTitleWithContent(
    titleTextValue: String,
    contentTextValue: String,
    size:Int) {
    Row {
        Text(
            text = titleTextValue,
            style = TextStyle(
                fontSize = size.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            text = contentTextValue,
            style = TextStyle(
                fontSize = size.sp,
            )
        )
    }
}