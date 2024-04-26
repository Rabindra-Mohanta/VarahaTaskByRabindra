package com.example.varahataskbyrabindra.util

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.getSystemService

//constants for permission check
object PermissionUtils {
    fun Context.isLocationPermissionAllow():Boolean
    {
        return !(ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED)
    }

    @SuppressLint("ServiceCast")
    fun Context.isGpsEnabled():Boolean
    {
        var isGpsEnabled = false
        try {
            val locationmanager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            isGpsEnabled = locationmanager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        }
        catch (e:Exception)
        {
            e.printStackTrace()
        }

        return isGpsEnabled
    }

}