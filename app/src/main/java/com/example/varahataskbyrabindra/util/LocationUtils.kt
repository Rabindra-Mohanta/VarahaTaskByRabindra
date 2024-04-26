package com.example.varahataskbyrabindra.util

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.activity.result.IntentSenderRequest
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority

object LocationUtils {
    @SuppressLint("MissingPermission", "LongLogTag")
    fun Context.createLocationRequest(
        settingsLauncher: ManagedActivityResultLauncher<IntentSenderRequest, ActivityResult>?,
        ) {

        val locationRequest = LocationRequest.create().apply {
            priority = Priority.PRIORITY_HIGH_ACCURACY
            interval = 1 * 1000
            isWaitForAccurateLocation = true

        }
        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val client = LocationServices.getSettingsClient(this)
        val task = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {

        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    settingsLauncher?.launch(
                        IntentSenderRequest.Builder(exception.resolution).build()
                    )
                } catch (e: Exception) {
                    // Ignore the error.
                }
            }
        }
    }
}