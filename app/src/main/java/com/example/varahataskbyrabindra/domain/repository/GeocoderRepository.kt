package com.example.varahataskbyrabindra.domain.repository

//interface
interface GeocoderRepository {
    suspend fun getAddress(latitude: Double, longitude: Double): String?

}