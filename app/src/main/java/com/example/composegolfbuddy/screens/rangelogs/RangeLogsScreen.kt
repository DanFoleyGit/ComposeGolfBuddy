
package com.example.composegolfbuddy.screens.rangelogs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.composegolfbuddy.designsystem.compents.RangeLogCard

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RangeLogsScreen(
    rangeLogsViewModel: RangeLogsViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        val createRangeLogsUiState by rangeLogsViewModel.createRangeLogsUiState.collectAsState()

        Scaffold {
            Column(horizontalAlignment = Alignment.End) {
                LazyColumn {
                    items(createRangeLogsUiState.rangeLogsList) { log ->
                        RangeLogCard(log)
                    }
                }
            }
        }
    }
}

