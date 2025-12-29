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
    val brand: String,
    val model: String,
    val iconResId: Int,
    val isFavorite: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)

fun DeviceEntity.toDomain(): Device {
    return Device(
        id = id,
        name = name,
        type = DeviceType.valueOf(type),
        brand = brand,
        model = model,
        iconResId = iconResId,
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
        brand = brand,
        model = model,
        iconResId = iconResId,
        isFavorite = isFavorite,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
