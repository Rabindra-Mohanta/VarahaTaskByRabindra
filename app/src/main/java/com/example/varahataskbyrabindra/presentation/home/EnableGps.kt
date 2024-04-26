package com.example.varahataskbyrabindra.presentation.home

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.varahataskbyrabindra.util.LocationUtils.createLocationRequest

@Composable
fun EnableGps(onEnabledGps:(Boolean)->Unit)
{
    val context = LocalContext.current
    val settingsLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = {
            when (it.resultCode) {
                Activity.RESULT_OK -> {
                 Log.e("rabindra","gpsEnableld"+true)
                }
                Activity.RESULT_CANCELED -> {
                    Toast.makeText(context, "GPS cancelled", Toast.LENGTH_LONG).show()
                }
            }
        }
    )

    LaunchedEffect(key1 = null) {
        context.createLocationRequest(settingsLauncher)
    }


}


