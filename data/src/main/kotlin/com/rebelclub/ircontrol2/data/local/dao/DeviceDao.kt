package com.rebelclub.ircontrol2.data.local.dao

import androidx.room.*
import com.rebelclub.ircontrol2.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    
    @Query("SELECT * FROM devices ORDER BY createdAt DESC")
    fun getAllDevices(): Flow<List<DeviceEntity>>
    
    @Query("SELECT * FROM devices WHERE id = :deviceId")
    suspend fun getDeviceById(deviceId: String): DeviceEntity?
    
    @Query("SELECT * FROM devices WHERE isFavorite = 1 ORDER BY name ASC")
    fun getFavoriteDevices(): Flow<List<DeviceEntity>>
    
    @Query("SELECT * FROM devices WHERE type = :type ORDER BY name ASC")
    fun getDevicesByType(type: String): Flow<List<DeviceEntity>>
    
    @Query("SELECT * FROM devices WHERE roomLocation = :room ORDER BY name ASC")
    fun getDevicesByRoom(room: String): Flow<List<DeviceEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: DeviceEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevices(devices: List<DeviceEntity>)
    
    @Update
    suspend fun updateDevice(device: DeviceEntity)
    
    @Delete
    suspend fun deleteDevice(device: DeviceEntity)
    
    @Query("DELETE FROM devices WHERE id = :deviceId")
    suspend fun deleteDeviceById(deviceId: String)
    
    @Query("UPDATE devices SET isFavorite = :isFavorite WHERE id = :deviceId")
    suspend fun updateFavoriteStatus(deviceId: String, isFavorite: Boolean)
    
    @Query("UPDATE devices SET isActive = :isActive WHERE id = :deviceId")
    suspend fun updateActiveStatus(deviceId: String, isActive: Boolean)
}