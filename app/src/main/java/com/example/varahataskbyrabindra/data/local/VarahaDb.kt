package com.example.varahataskbyrabindra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

//this is room database
@Database(entities = [UserEntity::class], version = 1)
abstract class VarahaDb : RoomDatabase() {
    abstract val userEntityDao: UserEntityDao
}