package com.rebelclub.ircontrol2.data.di

import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import com.rebelclub.ircontrol2.data.repository.DeviceRepositoryImpl
import com.rebelclub.ircontrol2.data.repository.IRCommandRepository
import com.rebelclub.ircontrol2.data.repository.IRCommandRepositoryImpl
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
    abstract fun bindDeviceRepository(
        deviceRepositoryImpl: DeviceRepositoryImpl
    ): DeviceRepository
    
    @Binds
    @Singleton
    abstract fun bindIRCommandRepository(
        irCommandRepositoryImpl: IRCommandRepositoryImpl
    ): IRCommandRepository
}
