package com.rebelclub.ircontrol2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.core.model.DeviceType

@Entity(tableName = "devices")
data class DeviceEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val type: String,
    val manufacturer: String,
    val model: String,
    val roomLocation: String,
    val isActive: Boolean,
    val isFavorite: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

fun DeviceEntity.toDomain(): Device {
    return Device(
        id = id,
        name = name,
        type = DeviceType.valueOf(type),
        manufacturer = manufacturer,
        model = model,
        roomLocation = roomLocation,
        isActive = isActive,
        isFavorite = isFavorite,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Device.toEntity(): DeviceEntity {
    return DeviceEntity(
        id = id,
        name = name,
        type = type.name,
        manufacturer = manufacturer,
        model = model,
        roomLocation = roomLocation,
        isActive = isActive,
        isFavorite = isFavorite,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
