package com.rebelclub.ircontrol2.data.di

import android.content.Context
import androidx.room.Room
import com.rebelclub.ircontrol2.data.local.IRControlDatabase
import com.rebelclub.ircontrol2.data.local.dao.DeviceDao
import com.rebelclub.ircontrol2.data.local.dao.IRCommandDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    
    @Provides
    @Singleton
    fun provideIRControlDatabase(
        @ApplicationContext context: Context
    ): IRControlDatabase {
        return Room.databaseBuilder(
            context,
            IRControlDatabase::class.java,
            "ir_control_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    
    @Provides
    @Singleton
    fun provideDeviceDao(database: IRControlDatabase): DeviceDao {
        return database.deviceDao()
    }
    
    @Provides
    @Singleton
    fun provideIRCommandDao(database: IRControlDatabase): IRCommandDao {
        return database.irCommandDao()
    }
}
