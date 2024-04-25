package com.example.varahataskbyrabindra.presentation.home
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.os.Build
import android.util.Log
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
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val geocoderRepository: GeocoderRepository,private val userRepository: UserRepository) : ViewModel() {
    var state by mutableStateOf(UserInfoState())
    val addressLiveData = MutableLiveData<String>()
    init {
        getAllUserList()
    }

    private fun getAllUserList() {
        viewModelScope.launch {
            state = state.copy(userDataList = userRepository.getUserListings())

        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            userRepository.deleteUser(id)
        }
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
    fun getMarkerAddressDetails(lat:Double, long:Double) {
        viewModelScope.launch {
          val address =  geocoderRepository.getAddress(lat,long)
            address?.let {
                addressLiveData.value = it
            }
        }

    }
}

