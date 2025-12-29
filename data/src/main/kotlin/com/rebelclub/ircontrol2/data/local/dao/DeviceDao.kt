package com.rebelclub.ircontrol2.data.local.dao

import androidx.room.*
import com.rebelclub.ircontrol2.data.local.entity.DeviceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeviceDao {
    @Query("SELECT * FROM devices ORDER BY createdAt DESC")
    fun getAllDevices(): Flow<List<DeviceEntity>>

    @Query("SELECT * FROM devices WHERE id = :deviceId")
    fun getDeviceById(deviceId: String): Flow<DeviceEntity?>

    @Query("SELECT * FROM devices WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteDevices(): Flow<List<DeviceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDevice(device: DeviceEntity)

    @Update
    suspend fun updateDevice(device: DeviceEntity)

    @Query("DELETE FROM devices WHERE id = :deviceId")
    suspend fun deleteDevice(deviceId: String)

    @Query("UPDATE devices SET isFavorite = :isFavorite WHERE id = :deviceId")
    suspend fun updateFavoriteStatus(deviceId: String, isFavorite: Boolean)
}