package com.example.varahataskbyrabindra.data.repository
import com.example.varahataskbyrabindra.data.local.VarahaDb
import com.example.varahataskbyrabindra.data.mapper.toUserData
import com.example.varahataskbyrabindra.data.mapper.toUserEntity
import com.example.varahataskbyrabindra.domain.model.UserData
import com.example.varahataskbyrabindra.domain.repository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

// implementation of UserRepository with dagger hilt
@Singleton
class UserRepositoryImpl @Inject constructor(private val roomDb: VarahaDb) : UserRepository {
    private val userEntityDao = roomDb.userEntityDao
    override suspend fun getUserListings(): List<UserData> {
        val userDataList = userEntityDao.getAllData().map {
            it.toUserData()
        }
        return userDataList
    }
    override suspend fun deleteUser(id: Long) {
        userEntityDao.deleteUser(id)
    }
    override  fun getDetails(id: Long): UserData {
        return userEntityDao.getUserDetails(id).toUserData()
    }
    override suspend fun insertUser(userData: UserData) {
        userEntityDao.insert(userData.toUserEntity())
    }
}