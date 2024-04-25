package com.example.varahataskbyrabindra.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.util.Constants
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val userList = homeViewModel.state.userDataList
    val openAlertDialog = rememberSaveable { mutableStateOf(false) }



    Box(modifier = Modifier.fillMaxSize())
    {
        val markerPosition = remember {
            mutableStateOf(LatLng(12.9716, 77.5946))
        }
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(markerPosition.value, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapClick = {
                //openAlertDialog.value = !openAlertDialog.value
                 homeViewModel.addUser(UserData("Rabindra","ff",14,"ff",it.latitude,it.longitude))
            }, onMyLocationClick = {

            })
        {
            userList.forEach { userData ->
                Marker(
                    state = MarkerState(LatLng(userData.latitude, userData.longitude)),
                    title = userData.name,
                    snippet = userData.address
                )
            }

            when {
                openAlertDialog.value -> {
                    ConfirmDialog(
                        onDismissRequest = { openAlertDialog.value = false },
                        onConfirmation = {
                            openAlertDialog.value = false
                        },
                        dialogTitle = Constants.txt_delete,
                        dialogText = Constants.msg_delete,
                        icon = Icons.Default.Info
                    )
                }
            }
        }
    }

}

