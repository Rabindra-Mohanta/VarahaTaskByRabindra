package com.example.varahataskbyrabindra.presentation.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices


@Composable
@SuppressLint("MissingPermission")
fun GetCurrentLocationOnce(
    onLocationRetrieved: (android.location.Location?) -> Unit,
) {
    val context = LocalContext.current
    val locationManager = LocalContext.current.getSystemService(LocationManager::class.java)
    val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    if(isGpsEnabled)
    {
        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
            onLocationRetrieved.invoke(it)
        }
    }
    else
    {
        EnableGps(){
            fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                onLocationRetrieved.invoke(it)
            }
        }
    }


}