package com.example.varahataskbyrabindra.domain.model

//this is data class for user
data class UserData(
    val id: Long? = null,
    val name: String = "",
    val relation: String = "",
    val age: String = "",
    val address: String = "",
    val latitude: Double? = null,
    val longitude: Double? = null
)
