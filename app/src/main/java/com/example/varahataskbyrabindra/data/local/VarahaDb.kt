package com.example.varahataskbyrabindra.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class VarahaDb:RoomDatabase() {
    abstract val userEntityDao:UserEntityDao
}