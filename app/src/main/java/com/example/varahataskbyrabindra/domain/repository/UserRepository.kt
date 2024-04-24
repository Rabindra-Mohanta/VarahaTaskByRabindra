package com.example.varahataskbyrabindra.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.varahataskbyrabindra.data.local.UserEntity
import com.example.varahataskbyrabindra.domain.model.UserData
import kotlinx.coroutines.flow.Flow


interface UserRepository
{
    suspend fun getUserListings():List<UserData>
    suspend fun deleteUser(id:Long)
     fun getDetails(id:Long):UserData
     suspend fun insertUser(userData: UserData)
}
