package com.example.varahataskbyrabindra.di

import com.example.varahataskbyrabindra.data.repository.GeocoderRepositoryImpl
import com.example.varahataskbyrabindra.data.repository.UserRepositoryImpl
import com.example.varahataskbyrabindra.domain.repository.GeocoderRepository
import com.example.varahataskbyrabindra.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl):UserRepository
    @Binds
    abstract fun bindGeocoderRepository(geocoderRepositoryImpl: GeocoderRepositoryImpl):GeocoderRepository

}