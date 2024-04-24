package com.example.varahataskbyrabindra.presentation.home
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    var state by mutableStateOf(UserInfoState())
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
}

