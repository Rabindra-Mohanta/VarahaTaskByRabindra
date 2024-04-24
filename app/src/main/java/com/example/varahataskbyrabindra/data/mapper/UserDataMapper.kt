package com.example.varahataskbyrabindra.data.mapper

import com.example.varahataskbyrabindra.data.local.UserEntity
import com.example.varahataskbyrabindra.domain.model.UserData


fun UserEntity.toUserData(): UserData {
    return UserData(
        name = name,
        relation = relation,
        age = age,
        address = address,
        latitude = latitude,
        longitude = longitude
    )
}

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

