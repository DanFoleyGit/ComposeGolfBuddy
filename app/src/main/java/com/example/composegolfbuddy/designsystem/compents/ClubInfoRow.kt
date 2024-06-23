package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.multiplatform.clubdistances.homeScreen.model.Club

@Composable
fun ClubInfoRow(club: Club, modifier: Modifier = Modifier) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Start,
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
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                        shape = CircleShape
                    )
                    .padding(top = 12.dp),
                textAlign = TextAlign.Center
            )
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.padding(2.dp)
            ) {
                Text(
                    text = "Loft: ${club.clubLoft}",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
                Text(
                    text = "Make: ${club.clubBrand}",
                    style = TextStyle(
                        fontSize = 16.sp
                    )
                )
            }
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(2.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "${club.distance} Yards",
                    style = TextStyle(
                        fontSize = 26.sp,
                    ),
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
    }
}