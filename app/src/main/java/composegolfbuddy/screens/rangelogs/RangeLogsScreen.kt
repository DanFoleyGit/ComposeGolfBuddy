
package composegolfbuddy.screens.rangelogs

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composegolfbuddy.designsystem.components.RangeLogCard
import composegolfbuddy.designsystem.components.SortButton
import composegolfbuddy.designsystem.components.SpeechBox

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RangeLogsScreen(
    rangeLogsViewModel: RangeLogsViewModel,
    modifier: Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val rangeLogsUiState by rangeLogsViewModel.rangeLogsUiState.collectAsState()

        Scaffold {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    ) {
                    SortButton(
                        trueText = "Date",
                        falseText = "Date",
                        onClick = {rangeLogsViewModel.toggleSortByDate()},
                        isSortActive = rangeLogsUiState.isSortedByDate
                    )
                    SortButton(
                        trueText = "Location",
                        falseText = "Location",
                        onClick = {rangeLogsViewModel.toggleSortByLocation()},
                        isSortActive = rangeLogsUiState.isSortedByLocation
                    )
                    SortButton(
                        trueText = "ASC",
                        falseText = "DSC",
                        onClick = {rangeLogsViewModel.toggleAscendingDescending()},
                        isSortActive = rangeLogsUiState.isASC,
                        icon = Icons.Filled.ArrowUpward
                    )
                }

                LazyColumn {
                    items(rangeLogsUiState.rangeLogsList) { log ->
                        RangeLogCard(
                            log,
                            deleteRangeLogById = { rangeLogsViewModel.deleteById(it) }
                        )
                    }
                }
            }

            Column(
                modifier.fillMaxHeight()
                    .padding(bottom = 16.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                androidx.compose.animation.AnimatedVisibility(
                    visible = rangeLogsUiState.displayHint,
                    enter = expandVertically(
                        expandFrom = Alignment.Bottom,
                        animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)

                    ),
                    exit = shrinkVertically(
                        shrinkTowards = Alignment.Bottom,
                        animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)
                    )
                ) {
                    SpeechBox(text = "Click here to create your first range Log")
                }
            }
        }
    }
}

