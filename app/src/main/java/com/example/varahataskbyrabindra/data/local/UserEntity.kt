package com.example.varahataskbyrabindra.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(@PrimaryKey(autoGenerate = true)  val int:Long,val name:String,val relation:String,val age:Int,val address:String,val latitude:Double,val longitude:Double)
