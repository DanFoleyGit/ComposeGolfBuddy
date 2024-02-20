package com.example.composegolfbuddy.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegolfbuddy.Greeting
import com.multiplatform.clubdistances.homeScreen.model.Club

@Composable
fun HomeScreen( modifier: Modifier = Modifier
//    .background(color = Color.White)
)
{
    val list = mutableListOf<Club>(
        Club("D","9", "Titleist", 240),
        Club("3W","15", "Titleist", 210),
        Club("7W","23", "Titleist", 190),
        Club("4i","28", "Titleist", 180),

    )

    LazyColumn {
        items(list) { club ->
            ClubInfoRow(club)
        }
    }
}

@Composable
fun ClubInfoRow(club: Club, modifier: Modifier = Modifier) {

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(4.dp)
        .background(
            color = MaterialTheme.colorScheme.primaryContainer,
            shape = RoundedCornerShape(4.dp)
        ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = club.clubName,
            style = TextStyle(
                fontSize = 30.sp,
            ),
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = CircleShape
                )
                .padding(8.dp),
            textAlign = TextAlign.Center
        )
        Column (
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.padding(8.dp)
        ){
            Text(text = "Loft: ${club.clubLoft}",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
            Text(text = "Make: ${club.clubBrand}",
                style = TextStyle(
                    fontSize = 16.sp
                )
            )
        }

        Text(
            text = "Yardage: ${club.distance}",
            style = TextStyle(
                fontSize = 26.sp,
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
//    ClubInfoRow(Club("D","46", "Titleist", 130))
    HomeScreen()
}