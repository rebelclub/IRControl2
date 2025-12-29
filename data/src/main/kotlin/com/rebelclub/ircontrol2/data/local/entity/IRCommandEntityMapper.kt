package com.rebelclub.ircontrol2.data.local.entity

import com.rebelclub.ircontrol2.core.model.IRCommand

fun IRCommandEntity.toDomain(): IRCommand {
    return IRCommand(
        id = id,
        deviceId = deviceId,
        name = name,
        category = category,
        protocol = protocol,
        hexCode = hexCode,
        frequency = frequency,
        dutyCycle = dutyCycle,
        isCustom = isCustom,
        order = order,
        createdAt = createdAt
    )
}

fun IRCommand.toEntity(): IRCommandEntity {
    return IRCommandEntity(
        id = id,
        deviceId = deviceId,
        name = name,
        category = category,
        protocol = protocol,
        hexCode = hexCode,
        frequency = frequency,
        dutyCycle = dutyCycle,
        isCustom = isCustom,
        order = order,
        createdAt = createdAt
    )
}
