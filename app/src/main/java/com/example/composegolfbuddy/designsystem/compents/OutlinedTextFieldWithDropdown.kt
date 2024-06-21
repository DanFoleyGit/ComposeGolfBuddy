package com.example.composegolfbuddy.designsystem.compents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutlinedTextFieldWithDropdown(
    initialValue: String = "",
    valueChanged: (String) -> Unit,
    retrieveClub: () -> Unit,
    indexChanged: (Int) -> Unit,
    selectedIndex: Int,
    isErrorState: Boolean = false,
    errorMessage: String,
    title: String = "",
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.Start) {
        Box(modifier = Modifier.clickable { expanded = true }) {
            OutlinedTextField(
                value = if (selectedIndex != -1) options[selectedIndex] else initialValue,
                onValueChange = {
                    valueChanged(it)
                },
                label = { Text(title) },
                isError = isErrorState,
                readOnly = true,
                singleLine = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Dropdown",
                        modifier = Modifier.clickable { expanded = true }
                    )
                }
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        valueChanged(option)
                        retrieveClub()
                        indexChanged(index)
                        expanded = false
                    }
                )
            }
        }

        if (isErrorState) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
