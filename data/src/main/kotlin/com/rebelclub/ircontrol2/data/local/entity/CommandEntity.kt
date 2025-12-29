package com.rebelclub.ircontrol2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rebelclub.ircontrol2.core.model.Command

@Entity(tableName = "commands")
data class CommandEntity(
    @PrimaryKey
    val id: String,
    val deviceId: String,
    val name: String,
    val irSignal: String,
    val iconResId: Int,
    val createdAt: Long,
    val updatedAt: Long
)

fun CommandEntity.toDomain(): Command {
    return Command(
        id = id,
        deviceId = deviceId,
        name = name,
        irSignal = irSignal,
        iconResId = iconResId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Command.toEntity(): CommandEntity {
    return CommandEntity(
        id = id,
        deviceId = deviceId,
        name = name,
        irSignal = irSignal,
        iconResId = iconResId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
