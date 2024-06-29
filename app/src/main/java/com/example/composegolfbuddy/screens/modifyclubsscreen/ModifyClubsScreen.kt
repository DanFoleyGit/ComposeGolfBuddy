package com.example.composegolfbuddy.screens.modifyclubsscreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composegolfbuddy.designsystem.compents.ActionButton
import com.example.composegolfbuddy.designsystem.compents.OutlinedTextFieldForInputs
import com.example.composegolfbuddy.designsystem.compents.OutlinedTextFieldWithDropdown
import com.example.composegolfbuddy.designsystem.compents.TextHeader
import com.example.composegolfbuddy.designsystem.compents.ToggleButton
import com.example.composegolfbuddy.screens.GbViewModel


@Composable
fun ModifyClubsScreen(viewModel: GbViewModel, modifier: Modifier = Modifier) {

    val state = viewModel.modifyClubsState.collectAsState()
    val showInfo = rememberSaveable { mutableStateOf(false) }
    val infoContainerHeight by animateDpAsState(
        targetValue = if (showInfo.value) 400.dp else 100.dp,
        animationSpec = tween(durationMillis = 300)
    )
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize()
        .verticalScroll(scrollState)
    ) {
        modifyClubsInfoContainer(
            showInformationField = showInfo.value
        ) { showInfo.value = !showInfo.value }

        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = infoContainerHeight.value.dp, bottom = 16.dp)
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
}
@Composable
fun modifyClubsInfoContainer(showInformationField: Boolean, toggleVisible: () -> Unit) {

    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(16.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { toggleVisible() },
        contentAlignment = Alignment.TopStart,

        ){

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!showInformationField) {
                    Text(
                        text = "Tips",
                        style = TextStyle(
                            fontSize = 20.sp, // Adjust size as needed
                        )
                    )
                    Icon(
                        imageVector = Icons.Default.QuestionMark,
                        contentDescription = "Info",
                        modifier = Modifier.padding(8.dp)
                    )

                } else {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Info",
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }

            AnimatedVisibility(
                visible = showInformationField,
                enter = expandVertically(
                    expandFrom = Alignment.Top,
                    animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)
                ),
                exit = shrinkVertically(
                    shrinkTowards = Alignment.Top,
                    animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)
                ),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = RoundedCornerShape(4.dp)
                        ),
                ) {
                    TextHeader("Create Clubs")
                    Text(
                        text = "- Select the club type from the drop down menu.\n" +
                                "- Enter the brand, club loft and Distance.\n" +
                                "- Additional distances can be enter such as a \'Grip-Down\' and \'Shoulder-To-Shoulder\'",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    TextHeader("Edit Clubs")
                    Text(
                        text = "- Select the club type you wish to edit fro the drop down menu and the details will auto fill.\n",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}
