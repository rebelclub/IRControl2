package com.rebelclub.ircontrol2.data.repository

import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.data.local.dao.DeviceDao
import com.rebelclub.ircontrol2.data.local.entity.toDomain
import com.rebelclub.ircontrol2.data.local.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface DeviceRepository {
    fun getAllDevices(): Flow<List<Device>>
    suspend fun getDeviceById(deviceId: String): Device?
    fun getFavoriteDevices(): Flow<List<Device>>
    fun getDevicesByType(type: String): Flow<List<Device>>
    fun getDevicesByRoom(room: String): Flow<List<Device>>
    suspend fun insertDevice(device: Device)
    suspend fun updateDevice(device: Device)
    suspend fun deleteDevice(device: Device)
    suspend fun deleteDeviceById(deviceId: String)
    suspend fun updateFavoriteStatus(deviceId: String, isFavorite: Boolean)
    suspend fun updateActiveStatus(deviceId: String, isActive: Boolean)
}

@Singleton
class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao
) : DeviceRepository {
    
    override fun getAllDevices(): Flow<List<Device>> {
        return deviceDao.getAllDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getDeviceById(deviceId: String): Device? {
        return deviceDao.getDeviceById(deviceId)?.toDomain()
    }
    
    override fun getFavoriteDevices(): Flow<List<Device>> {
        return deviceDao.getFavoriteDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getDevicesByType(type: String): Flow<List<Device>> {
        return deviceDao.getDevicesByType(type).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getDevicesByRoom(room: String): Flow<List<Device>> {
        return deviceDao.getDevicesByRoom(room).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun insertDevice(device: Device) {
        deviceDao.insertDevice(device.toEntity())
    }
    
    override suspend fun updateDevice(device: Device) {
        deviceDao.updateDevice(device.toEntity())
    }
    
    override suspend fun deleteDevice(device: Device) {
        deviceDao.deleteDevice(device.toEntity())
    }
    
    override suspend fun deleteDeviceById(deviceId: String) {
        deviceDao.deleteDeviceById(deviceId)
    }
    
    override suspend fun updateFavoriteStatus(deviceId: String, isFavorite: Boolean) {
        deviceDao.updateFavoriteStatus(deviceId, isFavorite)
    }
    
    override suspend fun updateActiveStatus(deviceId: String, isActive: Boolean) {
        deviceDao.updateActiveStatus(deviceId, isActive)
    }
}
