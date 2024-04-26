package com.example.varahataskbyrabindra.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//this id dao class
@Dao
interface UserEntityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE FROM userentity WHERE id=:id")
    suspend fun deleteUser(id: Long)

    @Query("SELECT * FROM userentity WHERE id=:id")
    fun getUserDetails(id: Long): UserEntity

    @Query("DELETE From userentity")
    suspend fun deleteAll()

    @Query("select * from userentity")
    suspend fun getAllData(): List<UserEntity>

}

