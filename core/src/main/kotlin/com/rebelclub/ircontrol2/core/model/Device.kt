package com.rebelclub.ircontrol2.core.model

enum class DeviceType {
    TV,
    AIR_CONDITIONER,
    FAN,
    SOUNDBAR,
    PROJECTOR,
    SET_TOP_BOX,
    DVD_PLAYER,
    AMPLIFIER,
    RECEIVER,
    OTHER
}

data class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    val manufacturer: String,
    val model: String,
    val roomLocation: String = "",
    val isActive: Boolean = true,
    val isFavorite: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
