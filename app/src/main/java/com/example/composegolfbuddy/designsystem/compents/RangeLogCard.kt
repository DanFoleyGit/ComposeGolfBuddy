package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegolfbuddy.model.RangeLog

@Composable
fun RangeLogCard(rangeLog: RangeLog) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        )

    ){
        var expanded by rememberSaveable { mutableStateOf(false) }

        Column(){
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row(horizontalArrangement = Arrangement.Start)
                {
                    Icon(
                        Icons.Filled.GolfCourse,
                        "description",
                        modifier = Modifier
                            .padding(4.dp)
                            .size(32.dp)
                    )
                    Column() {
                        Text(
                            text = rangeLog.location,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                        Text(
                            text = rangeLog.date,
                            style = TextStyle(
                                fontSize = 16.sp
                            )
                        )
                    }
                }
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End)
                {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Filled.DeleteOutline, "Floating action button.")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Delete") },
                            onClick = {
                                // Delete Post
                                expanded = false
                            }
                        )
                    }
                }
            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            ){
                BoldTextTitleWithContent(
                    "Goal: ",
                    rangeLog.goal,
                    16
                )
                BoldTextTitleWithContent(
                    "Balls hit: ",
                    rangeLog.ballsHit,
                    16
                )
                Spacer(modifier = Modifier.height(8.dp))
                RangeLogNoteWithScroll(rangeLog.summary, size = 16)
            }
        }
    }
}
