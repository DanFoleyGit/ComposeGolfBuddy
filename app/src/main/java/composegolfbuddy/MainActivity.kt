package composegolfbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import composegolfbuddy.designsystem.ui.theme.ComposeGolfBuddyTheme
import composegolfbuddy.screens.GbViewModel
import composegolfbuddy.screens.GolfBuddyScreen
import composegolfbuddy.screens.rangelogs.RangeLogsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<GbViewModel>()
        val rangeLogsViewModel by viewModels<RangeLogsViewModel>()

        setContent {
            ComposeGolfBuddyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GolfBuddyScreen(viewModel, rangeLogsViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        style = MaterialTheme.typography.headlineSmall,
        color = Color.Green
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeGolfBuddyTheme {
        Greeting("Android")
    }
}