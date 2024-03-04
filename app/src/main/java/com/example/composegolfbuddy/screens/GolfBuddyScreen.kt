package com.example.composegolfbuddy.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NoteAlt
import androidx.compose.material.icons.outlined.SportsGolf
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composegolfbuddy.screens.homeScreen.HomeScreen
import com.example.composegolfbuddy.screens.modifyclubsscreen.ModifyClubsScreen
import com.example.composegolfbuddy.screens.rangelogs.RangeLogsScreen


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

enum class GolfBuddyScreenNames() {
    Logs,
    Home,
    Clubs
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GolfBuddyScreen(
    viewModel: GbViewModel,
    navController: NavHostController = rememberNavController()
) {

    val items = listOf(
        BottomNavigationItem(
            title = "Range Logs",
            selectedIcon = Icons.Filled.NoteAlt,
            unSelectedIcon = Icons.Outlined.NoteAlt
        ),
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "clubs",
            selectedIcon = Icons.Filled.SportsGolf,
            unSelectedIcon = Icons.Outlined.SportsGolf
        )
    )

    var selectedIndex by rememberSaveable {
        mutableStateOf(1)
    }

//    Surface (
//        modifier = Modifier.fillMaxSize(),
//        color = MaterialTheme.colorScheme.background
//    ) {
        Scaffold(
            bottomBar = {
                NavigationBar() {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                                val enumVal = GolfBuddyScreenNames.values()[selectedIndex].name
                                navController.navigate(GolfBuddyScreenNames.values()[selectedIndex].name)
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unSelectedIcon
                                    },
                                    contentDescription = item.title
                                )
                            }
                        )
                    }
                }
            }
        ) {
            NavHost(
                navController = navController,
                startDestination = GolfBuddyScreenNames.Home.name,
                modifier = Modifier.padding(16.dp)
            ) {
                composable(route = GolfBuddyScreenNames.Home.name) {
                    HomeScreen(
                        viewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                composable(route = GolfBuddyScreenNames.Clubs.name) {
                    ModifyClubsScreen(
                        viewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                composable(route = GolfBuddyScreenNames.Logs.name) {
                    RangeLogsScreen(
                        modifier = Modifier
                            .fillMaxHeight()
                    )
                }
            }
        }
//    }

}

//@Preview(showBackground = true)
//@Composable
//fun GolfBuddyScreenPreview() {
//    GolfBuddyScreen()
//}