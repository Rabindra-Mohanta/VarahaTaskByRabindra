package com.example.varahataskbyrabindra.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


//this the table name
@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    val name: String,
    val relation: String,
    val age: String = "",
    val address: String,
    val latitude: Double?,
    val longitude: Double?
)
