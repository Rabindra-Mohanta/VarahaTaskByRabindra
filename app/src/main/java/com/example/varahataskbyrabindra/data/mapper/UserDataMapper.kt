package com.example.varahataskbyrabindra.data.mapper

import com.example.varahataskbyrabindra.data.local.UserEntity
import com.example.varahataskbyrabindra.domain.model.UserData

//this is extension for cover UserEntity to UserData room will return UserEntity but we need UserData
fun UserEntity.toUserData(): UserData {
    return UserData(
        id = id,
        name = name,
        relation = relation,
        age = age,
        address = address,
        latitude = latitude,
        longitude = longitude
    )
}
//this is extension for cover UserData to UserEntity room can save UserEntity so we using this
fun UserData.toUserEntity(): UserEntity {
    return UserEntity(
        name = name,
        relation = relation,
        age = age,
        address = address,
        latitude = latitude,
        longitude = longitude
    )
}

