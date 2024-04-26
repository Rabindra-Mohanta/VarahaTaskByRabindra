package com.example.varahataskbyrabindra.data.repository

import android.content.Context
import android.location.Geocoder
import com.example.varahataskbyrabindra.domain.repository.GeocoderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

// implementation of GeocoderRepository with dagger hilt it will give the user address
@Singleton
class GeocoderRepositoryImpl @Inject constructor(@ApplicationContext private val context: Context) :
    GeocoderRepository {
    private val geocoder by lazy { Geocoder(context, Locale.getDefault()) }
    override suspend fun getAddress(latitude: Double, longitude: Double): String? {
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return addresses?.firstOrNull()?.getAddressLine(0)
    }
}