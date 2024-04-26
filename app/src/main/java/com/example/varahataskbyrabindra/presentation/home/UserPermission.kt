package com.example.varahataskbyrabindra.presentation.home

import android.Manifest
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun UserPermission()
{
    // this is for permission
    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )
    if (locationPermissionState.allPermissionsGranted) {

        GetCurrentLocationOnce(){
            Log.e("rabindra","userLocation->"+it?.latitude)
            Log.e("rabindra","userLocation1->"+it?.longitude)
        }



    }

    else {
        LaunchedEffect(key1 = locationPermissionState) {
            locationPermissionState.launchMultiplePermissionRequest()

        }
    }
}
