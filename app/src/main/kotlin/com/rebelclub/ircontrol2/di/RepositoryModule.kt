package com.rebelclub.ircontrol2.di

import com.rebelclub.ircontrol2.data.repository.CommandRepository
import com.rebelclub.ircontrol2.data.repository.CommandRepositoryImpl
import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import com.rebelclub.ircontrol2.data.repository.DeviceRepositoryImpl
import com.rebelclub.ircontrol2.data.repository.IRDeviceRepository
import com.rebelclub.ircontrol2.data.repository.IRDeviceRepositoryImpl
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
    abstract fun bindCommandRepository(
        commandRepositoryImpl: CommandRepositoryImpl
    ): CommandRepository

    @Binds
    @Singleton
    abstract fun bindIRDeviceRepository(
        irDeviceRepositoryImpl: IRDeviceRepositoryImpl
    ): IRDeviceRepository
}