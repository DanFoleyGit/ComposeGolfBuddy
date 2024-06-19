
package com.example.composegolfbuddy.screens.rangelogs

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.GolfCourse
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.navigation.NavHostController
import com.example.composegolfbuddy.designsystem.compents.BoldTextTitleWithContent
import com.example.composegolfbuddy.designsystem.compents.RangeLogNoteWithScroll
import com.example.composegolfbuddy.model.RangeLog
import com.example.composegolfbuddy.screens.GolfBuddyScreenNames

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

        val rangeLog = RangeLog(
            "Location",
            "DD/MM/YY",
            "Hit it straight",
            "100",
            "Lorem Lipsum Lorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem LipsumLorem Lipsum"
        )
        val rangeLogList = listOf(rangeLog, rangeLog, rangeLog)

        Scaffold {

            Column(horizontalAlignment = Alignment.End) {
                Row() {
                    ElevatedButton(onClick = { navController.navigate(GolfBuddyScreenNames.CreateLogs.name) }) {
                        Text(
                            text = "Create",
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Icon(
                            Icons.Filled.Create,
                            "description",
                            modifier = Modifier
                                .padding(4.dp)
                                .size(26.dp)
                        )

                    }
                }
                LazyColumn {
                    items(rangeLogList) { log ->
                        RangeLogCard(log)
                    }
                }
            }
        }
    }
}

@Composable
fun RangeLogCard(rangeLog: RangeLog) {
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)
        .height(250.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        )

    ){
        var expanded by rememberSaveable { mutableStateOf(false) }

        Column(){
            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Row(horizontalArrangement = Arrangement.Start)
                {
                    Icon(Icons.Filled.GolfCourse,
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
//                            .width()
//                            .height(100.dp)
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
                    rangeLog.ballShit,
                    16
                )
                RangeLogNoteWithScroll(rangeLog.note, size = 16)
            }

        }
    }
}

