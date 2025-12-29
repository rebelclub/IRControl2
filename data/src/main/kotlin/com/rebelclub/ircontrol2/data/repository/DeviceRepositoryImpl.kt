package com.rebelclub.ircontrol2.data.repository

import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.data.local.dao.DeviceDao
import com.rebelclub.ircontrol2.data.local.entity.toDomain
import com.rebelclub.ircontrol2.data.local.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeviceRepositoryImpl @Inject constructor(
    private val deviceDao: DeviceDao
) : DeviceRepository {

    override fun getAllDevices(): Flow<List<Device>> {
        return deviceDao.getAllDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getDeviceById(deviceId: String): Flow<Device?> {
        return deviceDao.getDeviceById(deviceId).map { it?.toDomain() }
    }

    override fun getFavoriteDevices(): Flow<List<Device>> {
        return deviceDao.getFavoriteDevices().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addDevice(device: Device) {
        deviceDao.insertDevice(device.toEntity())
    }

    override suspend fun updateDevice(device: Device) {
        deviceDao.updateDevice(device.toEntity())
    }

    override suspend fun deleteDevice(deviceId: String) {
        deviceDao.deleteDevice(deviceId)
    }

    override suspend fun toggleFavorite(deviceId: String, isFavorite: Boolean) {
        deviceDao.updateFavoriteStatus(deviceId, isFavorite)
    }
}