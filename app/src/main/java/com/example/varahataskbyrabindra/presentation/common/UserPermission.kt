package com.example.varahataskbyrabindra.presentation.common

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UserPermission(currentLocation: (LatLng) -> Unit) {
    // this is for permission
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    if (locationPermissionState.allPermissionsGranted) {
        GetCurrentLocation() {
            currentLocation.invoke(it)
        }

    } else {
        LaunchedEffect(key1 = locationPermissionState) {
            locationPermissionState.launchMultiplePermissionRequest()
        }
    }
}
