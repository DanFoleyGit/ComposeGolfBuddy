package com.example.composegolfbuddy.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composegolfbuddy.Greeting

@Composable
fun ModifyClubsScreen( modifier: Modifier = Modifier) {

        Greeting(name = "ModifyClubsScreen")

//    Column (modifier = Modifier,
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(16.dp)) {
//        Text(text = "Hello Modify clubs Screen",
//            modifier = Modifier
//                .fillMaxWidth()
//                .size(20.dp))
//    }


}

@Preview(showBackground = true)
@Composable
fun ModifyClubsScreenPreview() {
    ModifyClubsScreen()
}