package com.example.composegolfbuddy.screens.homeScreen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composegolfbuddy.designsystem.compents.ClubInfoRow
import com.example.composegolfbuddy.screens.GbViewModel

@Composable
fun HomeScreen(
    viewModel: GbViewModel,
    modifier: Modifier = Modifier
) {
    val homeScreenUiState by viewModel.homeScreenState.collectAsState()

    LazyColumn {
        items(homeScreenUiState.clubList) { club ->
            ClubInfoRow(club)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen(viewModel)
}