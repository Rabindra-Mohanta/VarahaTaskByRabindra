package com.example.varahataskbyrabindra.presentation.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.varahataskbyrabindra.util.Constants


//this dialog is for confirmation of deletion
@Composable
fun ConfirmDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = { Icon(imageVector = icon, contentDescription = Constants.Txt_Dialog_Icon) },
        title = { Text(text = dialogTitle) },
        text = { Text(text = Constants.msg_delete) },
        onDismissRequest = { onDismissRequest() },
        confirmButton = {
            TextButton(onClick = { onConfirmation() }) { Text(Constants.Txt_Confirm) }
        },
        dismissButton = { TextButton(onClick = { onDismissRequest() }) { Text(Constants.Txt_Dismiss) } })
}





