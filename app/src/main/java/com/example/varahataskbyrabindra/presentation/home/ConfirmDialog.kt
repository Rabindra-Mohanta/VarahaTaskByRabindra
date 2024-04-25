package com.example.varahataskbyrabindra.presentation.home
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.varahataskbyrabindra.util.Constants


@Composable
fun ConfirmDialog(onDismissRequest:()->Unit,onConfirmation:()->Unit,dialogTitle:String,dialogText:String,icon:ImageVector)
{
    var textStr = ""
AlertDialog(icon = { Icon(imageVector = icon,contentDescription = Constants.Txt_Dialog_Icon )}, title = { Text(
    text = dialogTitle
)}, text = { TextField(value = textStr, onValueChange = {textStr = it} , label = { Text(text = "Enter Name")})},onDismissRequest = { onDismissRequest()}, confirmButton = { TextButton(onClick = { onConfirmation() }) {
    Text(Constants.Txt_Confirm)

} }, dismissButton = { TextButton(onClick = { onDismissRequest() }) {
    Text(Constants.Txt_Dismiss)
}})
}
//var text by remember { mutableStateOf("") }
//val userInputs = mutableListOf(mutableStateOf(""), mutableStateOf(""))
// Adjust the number of mutableStateOf instances as needed
//Column(modifier = Modifier.fillMaxWidth()) {
//    userInputs.forEachIndexed { index, userInput ->
//        TextField(
//            value = userInput.value,
//            onValueChange = { userInputs[index].value = it },
//            label = { Text("Input Field ${index + 1}") }
//        )
//    }
//}




