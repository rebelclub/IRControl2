package com.rebelclub.ircontrol2.data.local.entity

import com.rebelclub.ircontrol2.core.model.Device

fun DeviceEntity.toDomain(): Device {
    return Device(
        id = id,
        name = name,
        type = type,
        brand = brand,
        model = model,
        room = room,
        iconResId = iconResId,
        isFavorite = isFavorite,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Device.toEntity(): DeviceEntity {
    return DeviceEntity(
        id = id,
        name = name,
        type = type,
        brand = brand,
        model = model,
        room = room,
        iconResId = iconResId,
        isFavorite = isFavorite,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
