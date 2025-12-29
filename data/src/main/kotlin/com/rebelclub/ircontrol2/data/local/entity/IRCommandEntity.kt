package com.rebelclub.ircontrol2.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import androidx.room.Index
import com.rebelclub.ircontrol2.core.model.IRCommand
import com.rebelclub.ircontrol2.core.model.CommandCategory

@Entity(
    tableName = "ir_commands",
    foreignKeys = [
        ForeignKey(
            entity = DeviceEntity::class,
            parentColumns = ["id"],
            childColumns = ["deviceId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["deviceId"])]
)
data class IRCommandEntity(
    @PrimaryKey
    val id: String,
    val deviceId: String,
    val name: String,
    val category: String,
    val frequency: Int,
    val pattern: String,
    val description: String
)

fun IRCommandEntity.toDomain(): IRCommand {
    return IRCommand(
        id = id,
        deviceId = deviceId,
        name = name,
        category = CommandCategory.valueOf(category),
        frequency = frequency,
        pattern = pattern.split(",").map { it.toInt() }.toIntArray(),
        description = description
    )
}

fun IRCommand.toEntity(): IRCommandEntity {
    return IRCommandEntity(
        id = id,
        deviceId = deviceId,
        name = name,
        category = category.name,
        frequency = frequency,
        pattern = pattern.joinToString(","),
        description = description
    )
}
