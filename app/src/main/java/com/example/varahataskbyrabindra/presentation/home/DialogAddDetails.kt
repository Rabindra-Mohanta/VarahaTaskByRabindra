package com.example.varahataskbyrabindra.presentation.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
@Composable
fun DialogExample() {
    // Mutable state to track whether the dialog is open
    val showDialog = mutableStateOf(false)

    // Function to toggle the dialog state
    fun toggleDialog() {
        showDialog.value = !showDialog.value
    }

    // Composable function for the UI
    Column {
        Button(onClick = { toggleDialog() }) {
            Text("Open Dialog")
        }
        // Show the dialog if showDialog is true
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when clicked outside or back button pressed
                    toggleDialog()
                },
                title = {
                    Text(text = "Dialog Title")
                },
                text = {
                    Text(text = "Dialog message")
                },
                confirmButton = {
                    Button(onClick = {
                        // Handle confirm button click
                        toggleDialog()
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        // Handle dismiss button click
                        toggleDialog()
                    }) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }
}