package com.example.varahataskbyrabindra.presentation.home
import android.Manifest
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.varahataskbyrabindra.R
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.util.Constants
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.qualifiers.ApplicationContext


@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    // list of user fetch from room db
    val userList = homeViewModel.state.userDataList
    val openAlertDialog = rememberSaveable { mutableStateOf(false) }
    val address by homeViewModel.addressLiveData.observeAsState()
    var askLocationPermission by remember { mutableStateOf(false) }


    Box(modifier = Modifier.fillMaxSize())
    {
        val markerPosition = remember {
            mutableStateOf(LatLng(12.9716, 77.5946))
        }
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(markerPosition.value, 10f)
        }


        GoogleMap(
            modifier = Modifier.fillMaxSize() ,
            cameraPositionState = cameraPositionState, uiSettings = MapUiSettings(zoomControlsEnabled = false),
            onMapClick = {
                //openAlertDialog.value = !openAlertDialog.value
               homeViewModel.getMarkerAddressDetails(it.latitude,it.longitude)
                  Log.e("rabindra","selectedAddress->"+address)
                 homeViewModel.addUser(UserData("Rabindra","ff",14,"ff",it.latitude,it.longitude))
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
        Icon(modifier = Modifier
            .align(alignment = Alignment.BottomEnd)
            .size(40.dp)
            .padding(end = 10.dp, bottom = 10.dp)
            .clickable {
                askLocationPermission = !askLocationPermission
            },painter = painterResource(id = R.drawable.icon_gps), contentDescription = "gpc" )

           if(askLocationPermission)
           {
               UserPermission()
           }
          else
           {
               UserPermission()
           }
    }


}








