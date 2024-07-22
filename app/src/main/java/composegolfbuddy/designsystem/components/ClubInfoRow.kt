package composegolfbuddy.designsystem.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composegolfbuddy.model.Club

@Composable
fun ClubInfoRow(
    club: Club,
    modifier: Modifier = Modifier,
    deleteClub: (Club) -> Unit,
) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }
    var isDeleteExpanded by rememberSaveable { mutableStateOf(false) }

    Box(
        Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                isExpanded = !isExpanded
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = MaterialTheme.colorScheme.surfaceContainerLow,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center,
            ){
                Text(
                    text = club.clubName,
                    style = TextStyle(
                        fontSize = 30.sp,
                    ),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "${club.distance} Yards",
                style = TextStyle(
                    fontSize = 26.sp,
                )
            )

            ToggleButton(
                modifier = Modifier.size(30.dp),
                toggleOn = isExpanded,
                onClick = { isExpanded = !isExpanded }
            )
        }

        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(
                expandFrom = Alignment.Top,
                initialHeight = { 90 },
                animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)

            ),
            exit = shrinkVertically(
                shrinkTowards = Alignment.Top,
                animationSpec = SpringSpec(stiffness = Spring.StiffnessLow)
            )
        ) {
            Box(
                modifier
                    .fillMaxSize()
                    .padding(top = 92.dp, end = 10.dp, start = 10.dp, bottom = 10.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                    )
                    .clickable {
                        isExpanded = !isExpanded
                    }
            ) {
                Column(modifier = modifier.align(Alignment.BottomEnd)) {
                    IconButton(onClick = { isDeleteExpanded = true }) {
                        Icon(Icons.Filled.DeleteOutline, "Floating action button.")
                    }
                    DropdownMenu(
                        expanded = isDeleteExpanded,
                        onDismissRequest = { isDeleteExpanded = false },
                        modifier = Modifier
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Delete") },
                            onClick = {
                                deleteClub(club)
                                isDeleteExpanded = false
                            }
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    horizontalAlignment = Alignment.Start
                ) {
                    if(club.distance2.isNotBlank()) {
                        BoldTextTitleWithContent("GripDown: ", club.distance2 + " Yards", 20)
                        Spacer(modifier = modifier.height(8.dp))
                    }
                    if(club.distance3.isNotBlank()) {
                        BoldTextTitleWithContent("Shoulder-Shoulder: ", club.distance3 + " Yards", 20
                        )
                        Spacer(modifier = modifier.height(8.dp))
                    }
                    BoldTextTitleWithContent("Make: ", club.clubBrand, 20 )
                    Spacer(modifier = modifier.height(8.dp))
                    BoldTextTitleWithContent("Loft: ", club.clubLoft, 20 )
                }
            }
        }
    }
}
