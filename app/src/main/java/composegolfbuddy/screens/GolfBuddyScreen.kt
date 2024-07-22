package composegolfbuddy.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material.icons.filled.NoteAlt
import androidx.compose.material.icons.filled.SportsGolf
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.NoteAdd
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
import composegolfbuddy.screens.createrangelog.CreateRangeLogScreen
import composegolfbuddy.screens.homeScreen.HomeScreen
import composegolfbuddy.screens.modifyclubsscreen.ModifyClubsScreen
import composegolfbuddy.screens.rangelogs.RangeLogsScreen
import composegolfbuddy.screens.rangelogs.RangeLogsViewModel


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

enum class GolfBuddyScreenNames() {
    Home,
    Clubs,
    Logs,
    CreateLogs
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GolfBuddyScreen(
    gbViewModel: GbViewModel,
    rangeLogsViewModel: RangeLogsViewModel,
    navController: NavHostController = rememberNavController()
) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        BottomNavigationItem(
            title = "clubs",
            selectedIcon = Icons.Filled.SportsGolf,
            unSelectedIcon = Icons.Outlined.SportsGolf
        ),
        BottomNavigationItem(
            title = "Range Logs",
            selectedIcon = Icons.Filled.NoteAlt,
            unSelectedIcon = Icons.Outlined.NoteAlt
        ),
        BottomNavigationItem(
            title = "Create Logs",
            selectedIcon = Icons.Filled.NoteAdd,
            unSelectedIcon = Icons.Outlined.NoteAdd
        )
    )

    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar() {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
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
    ) { innerPadding ->
        Box (modifier = Modifier.padding(innerPadding)){
            NavHost(
                navController = navController,
                startDestination = GolfBuddyScreenNames.Home.name,
                modifier = Modifier.padding(16.dp)
            ) {
                composable(route = GolfBuddyScreenNames.Home.name) {
                    HomeScreen(
                        gbViewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                composable(route = GolfBuddyScreenNames.Clubs.name) {
                    ModifyClubsScreen(
                        gbViewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                composable(route = GolfBuddyScreenNames.Logs.name) {
                    RangeLogsScreen(
                        rangeLogsViewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
                composable(route = GolfBuddyScreenNames.CreateLogs.name) {
                    CreateRangeLogScreen(
                        rangeLogsViewModel,
                        modifier = Modifier.fillMaxHeight()
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GolfBuddyScreenPreview() {
//    GolfBuddyScreen()
//}