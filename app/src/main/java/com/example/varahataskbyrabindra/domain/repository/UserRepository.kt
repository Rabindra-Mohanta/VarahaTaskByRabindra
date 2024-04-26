package com.example.varahataskbyrabindra.domain.repository

import com.example.varahataskbyrabindra.domain.model.UserData

interface UserRepository {
    suspend fun getUserListings(): List<UserData>
    suspend fun deleteUser(id: Long)
    fun getDetails(id: Long): UserData
    suspend fun insertUser(userData: UserData)
}
