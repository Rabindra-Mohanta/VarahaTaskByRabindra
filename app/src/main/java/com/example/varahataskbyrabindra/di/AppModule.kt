package com.example.varahataskbyrabindra.di
import android.app.Application
import androidx.room.Room
import com.example.varahataskbyrabindra.data.local.VarahaDb
import com.example.varahataskbyrabindra.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideVarahaDb(app: Application): VarahaDb {
        return Room.databaseBuilder(app, VarahaDb::class.java, Constants.DB_NAME).build()
    }
}