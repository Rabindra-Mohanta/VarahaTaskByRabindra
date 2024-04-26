package com.example.varahataskbyrabindra.presentation.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.varahataskbyrabindra.R
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.presentation.common.ConfirmDialog
import com.example.varahataskbyrabindra.presentation.common.UserPermission
import com.example.varahataskbyrabindra.util.Constants
import com.example.varahataskbyrabindra.util.PermissionUtils.isLocationPermissionAllow
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    // list of user fetch from room db
    val userList = homeViewModel.state.userDataList
    //for add details dialog on click for map means for new marker
    val openAddDetailsDialog = rememberSaveable { mutableStateOf(false) }
    //delete dialog
    val openDeleteDialog = rememberSaveable { mutableStateOf(false) }
    //address from geoCoder
    val address by homeViewModel.addressLiveData.observeAsState()
    //ask permission or not
    var askLocationPermission by remember { mutableStateOf(false) }
    //context
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize())
    {
        val currentLatLng = remember {
            // this lat lng is default for bangalore 12.9716,77.5946
            mutableStateOf(LatLng(12.9716, 77.5946))
        }
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(currentLatLng.value, 10f)
        }
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(zoomControlsEnabled = false),
            onMapClick = {
                openAddDetailsDialog.value = !openAddDetailsDialog.value
                homeViewModel.getMarkerAddressDetails(it.latitude, it.longitude)
            })
        {
            userList.forEach { userData ->
                Marker(
                    state = MarkerState(LatLng(userData.latitude!!, userData.longitude!!)),
                    title = userData.name,
                    snippet = "",
                    onClick = { marker ->
                        homeViewModel.userDeleteId = userData.id
                        openDeleteDialog.value = !openDeleteDialog.value
                        true
                    }
                )
            }

            when {
                openDeleteDialog.value -> {
                    //show confirm dialog for delete
                    ConfirmDialog(
                        onDismissRequest = { openDeleteDialog.value = false },
                        onConfirmation = {
                            homeViewModel.deleteUser()
                            Toast.makeText(
                                context,
                                Constants.msg_succes_deleted,
                                Toast.LENGTH_SHORT
                            ).show()
                            homeViewModel.getAllUserList()
                            openDeleteDialog.value = false
                        },
                        dialogTitle = Constants.txt_delete,
                        icon = Icons.Default.Info
                    )
                }

                openAddDetailsDialog.value -> {
                    //show add dialog if true
                    DialogAddDetails(
                        UserData(
                            address = address ?: "",
                            latitude = homeViewModel.userLat,
                            longitude = homeViewModel.userLng
                        ), { isShow ->
                            openAddDetailsDialog.value = isShow
                        }
                    )
                    { data ->
                        //add user to db
                        Toast.makeText(context, Constants.msg_succes_added, Toast.LENGTH_SHORT)
                            .show()
                        homeViewModel.addUser(data)
                    }
                }
            }
        }
        Icon(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .size(40.dp)
                .padding(end = 10.dp, bottom = 10.dp)
                .clickable {
                    askLocationPermission = !askLocationPermission
                },
            tint = colorResource(id = R.color.teal_200),
            painter = painterResource(id = R.drawable.icon_gps),
            contentDescription = "gpc"
        )
        if (askLocationPermission) {
            UserPermission()
            {
                currentLatLng.value = it
                cameraPositionState.position =
                    CameraPosition.fromLatLngZoom(currentLatLng.value, 15f)
                cameraPositionState.cameraMoveStartedReason

            }

        } else {
            //if permission is allow than only
            if (context.isLocationPermissionAllow()) {
                UserPermission()
                {
                    currentLatLng.value = it
                    cameraPositionState.position =
                        CameraPosition.fromLatLngZoom(currentLatLng.value, 15f)
                    cameraPositionState.cameraMoveStartedReason

                }
            }

        }
    }
}








