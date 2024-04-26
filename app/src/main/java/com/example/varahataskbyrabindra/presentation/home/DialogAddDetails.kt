package com.example.varahataskbyrabindra.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.varahataskbyrabindra.domain.model.UserData


//dialog for enter the name address etc
@Composable
fun DialogAddDetails(
    userData: UserData,
    showDialog: (Boolean) -> Unit,
    saveData: (UserData) -> Unit
) {
    val nameStr = remember { mutableStateOf(userData.name) }
    val relationStr = remember { mutableStateOf(userData.relation) }
    val ageStr = remember { mutableStateOf(userData.age) }
    val addressStr = remember { mutableStateOf(userData.address) }


    Dialog(onDismissRequest = {
        showDialog.invoke(
            false
        )
    }, properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)) {
        Surface(
            shape = RoundedCornerShape(16.dp),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "User details",
                        modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        value = nameStr.value,
                        onValueChange = { nameStr.value = it },
                        label = { Text(text = "Enter Name") })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = relationStr.value,
                        onValueChange = { relationStr.value = it },
                        label = { Text(text = "Enter Relation") })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = ageStr.value,
                        onValueChange = { ageStr.value = it },
                        label = { Text(text = "Enter Age") })
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = addressStr.value,
                        onValueChange = { addressStr.value = it },
                        label = { Text(text = "Enter Address") })
                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        modifier = Modifier
                            .height(40.dp)
                            .width(100.dp)
                            .align(alignment = Alignment.CenterHorizontally), onClick = {
                            saveData.invoke(
                                UserData(
                                    name = nameStr.value,
                                    relation = relationStr.value,
                                    age = ageStr.value,
                                    address = addressStr.value,
                                    latitude = userData.latitude,
                                    longitude = userData.longitude
                                )
                            )
                            showDialog.invoke(false)
                        }) {
                        Text(text = "Save")
                    }
                }


            }
        }

        fun validateInput(textValue: String): String? {
            return if (textValue.isNotEmpty()) return null else "required field"
        }
    }
}