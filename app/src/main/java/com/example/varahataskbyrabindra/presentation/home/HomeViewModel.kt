package com.example.varahataskbyrabindra.presentation.home

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.domain.repository.GeocoderRepository
import com.example.varahataskbyrabindra.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val geocoderRepository: GeocoderRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    var state by mutableStateOf(UserInfoState())
    val addressLiveData = MutableLiveData<String>()
    //for save userLat went they clicked on map
    var userLat: Double? = null
    //for save userLng went they clicked on map
    var userLng: Double? = null
    //for save userId when deletetion
    var userDeleteId: Long? = null

    init {
        //get all data
        getAllUserList()
    }

    fun getAllUserList() {
        viewModelScope.launch {
            state = state.copy(userDataList = userRepository.getUserListings())
        }
    }

    fun deleteUser() {
        viewModelScope.launch { userDeleteId?.let { userRepository.deleteUser(it) } }
    }

    fun getUserDetails(id: Long): UserData {
        return userRepository.getDetails(id)
    }

    fun addUser(userData: UserData) {
        viewModelScope.launch {
            userRepository.insertUser(userData)
            //get data again
            getAllUserList()
        }
    }

    @SuppressLint("SuspiciousIndentation")
    fun getMarkerAddressDetails(lat: Double, long: Double) {
        userLat = lat
        userLng = long
        viewModelScope.launch {
            val address = geocoderRepository.getAddress(lat, long)
            address?.let {
                addressLiveData.value = it
            }
        }
    }
}

