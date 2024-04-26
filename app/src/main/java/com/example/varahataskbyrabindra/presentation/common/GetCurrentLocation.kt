package com.example.varahataskbyrabindra.presentation.common

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.varahataskbyrabindra.util.Constants
import com.example.varahataskbyrabindra.util.LocationUtils.createLocationRequest
import com.example.varahataskbyrabindra.util.LocationUtils.fetchLastLocation
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng

//get current location and ask user to enable gps
@Composable
fun GetCurrentLocation(currentLocation: (LatLng) -> Unit) {
    val context = LocalContext.current
    val fusedLocationProviderClient =
        remember { LocationServices.getFusedLocationProviderClient(context) }
    //call back of location
    val locationCallback = remember {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.lastLocation?.let {
                    currentLocation.invoke(LatLng(it.latitude, it.longitude))
                }
                fusedLocationProviderClient.removeLocationUpdates(this)//it is removed because we need current location at a time only
            }
        }
    }
    //for gps enable
    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                    //if gps on
                    context.fetchLastLocation(
                        fusedLocationClient = fusedLocationProviderClient,
                        settingsLauncher = null,
                        location = {
                        },
                        locationCallback = locationCallback
                    )
                }

                Activity.RESULT_CANCELED -> {
                    //if gps canceled
                    Toast.makeText(context, Constants.text_gps_cancelled, Toast.LENGTH_LONG).show()
                }
            }
        }
    )
    LaunchedEffect(key1 = null) {
        //request for gps on
        context.createLocationRequest(
            settingsLauncher,
            fusedLocationProviderClient,
            locationCallback
        )
    }

}


