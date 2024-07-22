package composegolfbuddy.screens.modifyclubsscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composegolfbuddy.designsystem.components.ActionButton
import composegolfbuddy.designsystem.components.InfoDialog
import composegolfbuddy.designsystem.components.OutlinedTextFieldForInputs
import composegolfbuddy.designsystem.components.OutlinedTextFieldWithDropdown
import composegolfbuddy.designsystem.components.ToggleButton
import composegolfbuddy.screens.GbViewModel


@Composable
fun ModifyClubsScreen(viewModel: GbViewModel, modifier: Modifier = Modifier) {

    val state = viewModel.modifyClubsState.collectAsState()
    val showInfo = rememberSaveable { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(start = 200.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(20.dp)
                    ).clickable { showInfo.value = !showInfo.value }
            ) {
                Row(
                    modifier = Modifier.padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.QuestionMark,
                        contentDescription = "Info",
                    )
                    Text(
                        text = "Tips",
                        style = TextStyle(
                            fontSize = 20.sp, // Adjust size as needed
                        )
                    )
                }
            }
        }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp, bottom = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            OutlinedTextFieldWithDropdown(
                valueChanged = { viewModel.updateClubTypeValue(it) },
                indexChanged = { viewModel.updateClubTypeIndex(it) },
                retrieveClub = { viewModel.retrieveSelectedClub() },
                selectedIndex = viewModel.clubTypeIndex,
                isErrorState = state.value.clubTypeError,
                errorMessage = state.value.clubTypeErrorMessage,
                title = "Type",
                options = viewModel.clubTypesList
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextFieldForInputs(
                initialValue = viewModel.clubBrandInput,
                valueChanged = { viewModel.updateClubBrandValue(it) },
                isErrorState = state.value.clubBrandError,
                errorMessage = state.value.clubBrandErrorMessage,
                title = "Brand",
                maxLength = 16,
                modifier = Modifier
            )
            OutlinedTextFieldForInputs(
                initialValue = viewModel.clubLoftInput,
                valueChanged = { viewModel.updateClubLoftValue(it) },
                isErrorState = state.value.clubLoftError,
                errorMessage = state.value.clubLoftErrorMessage,
                title = "Club Loft",
                maxLength = 12,
                modifier = Modifier
            )
            OutlinedTextFieldForInputs(
                initialValue = viewModel.distanceInput,
                valueChanged = { viewModel.updateClubDistanceValue(it) },
                isErrorState = state.value.distanceError,
                errorMessage = state.value.distanceErrorMessage,
                title = "Distance",
                maxLength = 3,
                modifier = Modifier
            )

            AnimatedVisibility(state.value.showExtraDistances) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom
                ) {
                    OutlinedTextFieldForInputs(
                        initialValue = viewModel.distanceInput2,
                        valueChanged = { viewModel.updateClubDistance2Value(it) },
                        isErrorState = state.value.distanceError,
                        errorMessage = state.value.distanceErrorMessage,
                        title = "Grip-Down Distance",
                        maxLength = 3,
                        modifier = Modifier
                    )

                    OutlinedTextFieldForInputs(
                        initialValue = viewModel.distanceInput3,
                        valueChanged = { viewModel.updateClubDistance3Value(it) },
                        isErrorState = state.value.distanceError,
                        errorMessage = state.value.distanceErrorMessage,
                        title = "Shoulder-To-Shoulder Distance",
                        maxLength = 3,
                        modifier = Modifier
                    )
                }
            }


            ToggleButton(
                modifier = Modifier.size(40.dp),
                toggleOn = state.value.showExtraDistances,
                onClick = { viewModel.toggleShowExtraDistances() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            ActionButton(
                doAction = { viewModel.processClubInputs() },
                icon = Icons.Filled.Add,
                textValue = "Add CLub"
            )
        }
    }

    when {
        showInfo.value -> {
            InfoDialog(
                onDismissRequest = { showInfo.value = false },
                onConfirmation = { showInfo.value = false },
                dialogTitle = "Tips and Tricks",
                dialogText = "Create Clubs\n" +
                        "- Select the club type from the drop down menu.\n" +
                        "- Enter the brand, club loft and Distance.\n" +
                        "- Additional distances can be enter such as a \'Grip-Down\' and \'Shoulder-To-Shoulder\'\n\n" +
                        "Edit Clubs\n" +
                        "- Select the club type you wish to edit fro the drop down menu and the details will auto fill.",
                icon = Icons.Default.Info
            )
        }
    }
}
