package com.example.composegolfbuddy.screens.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegolfbuddy.designsystem.compents.ClubInfoRow
import com.example.composegolfbuddy.designsystem.compents.TextHeader
import com.example.composegolfbuddy.screens.GbViewModel

@Composable
fun HomeScreen(
    viewModel: GbViewModel,
    modifier: Modifier = Modifier
) {
    val homeScreenUiState by viewModel.homeScreenState.collectAsState()

    HomeScreenInfoContainer(homeScreenUiState.showInformationField)
    
    LazyColumn {
        items(homeScreenUiState.clubList) { club ->
            ClubInfoRow(
                club = club,
                deleteClub =  { viewModel.deleteClub(club) }
            )
        }
    }
}

@Composable
fun HomeScreenInfoContainer(showInformationField: Boolean) {

    AnimatedVisibility(
        visible = showInformationField,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)

        ),
        exit = shrinkVertically(
            shrinkTowards = Alignment.Top,
            animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(8.dp)
        ) {
            TextHeader("Welcome to the Golf Buddy App!")
            Text(
                text = "- The app to help you train and get better",
                modifier = Modifier.padding(start = 16.dp)
            )

            TextHeader(textValue = "Home Screen")
            Text(
                text = "- Shows you your clubs and the information you've entered.",
                modifier = Modifier.padding(start = 16.dp)
            )

            TextHeader(textValue = "Clubs Screen")
            Text(
                text = "- Allows you to create your own clubs and track info and stats.",
                modifier = Modifier.padding(start = 16.dp)
            )

            TextHeader(textValue = "Range Logs")
            Text(
                text = "- A tool for keeping note of each session.",
                modifier = Modifier.padding(start = 16.dp)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    HomeScreen(viewModel)
}