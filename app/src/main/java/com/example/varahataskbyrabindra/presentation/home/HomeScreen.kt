package com.example.varahataskbyrabindra.presentation.home
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.varahataskbyrabindra.domain.model.UserData
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    val userList = homeViewModel.state.userDataList

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
                homeViewModel.addUser(UserData("Rabindra","ff",14,"ff",it.latitude,it.longitude))
            })
        {
            userList.forEach {userData ->
                Marker(position = LatLng(userData.latitude,userData.longitude), title = userData.name, snippet = userData.address)

            }
        }
    }
}

