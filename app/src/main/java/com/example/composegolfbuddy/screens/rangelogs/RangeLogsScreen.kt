
package com.example.composegolfbuddy.screens.rangelogs

import android.annotation.SuppressLint
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.composegolfbuddy.designsystem.compents.RangeLogCard
import com.example.composegolfbuddy.designsystem.compents.SpeechBox

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

        val createRangeLogsUiState by rangeLogsViewModel.createRangeLogsUiState.collectAsState()

        Scaffold {
            Column(horizontalAlignment = Alignment.End) {
                LazyColumn {
                    items(createRangeLogsUiState.rangeLogsList) { log ->
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
                    visible = createRangeLogsUiState.displayHint,
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

