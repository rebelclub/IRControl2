package com.rebelclub.ircontrol2.core.model

data class IRCommand(
    val id: String,
    val deviceId: String,
    val name: String,
    val category: CommandCategory,
    val frequency: Int,
    val pattern: IntArray,
    val description: String = ""
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as IRCommand
        if (id != other.id) return false
        if (deviceId != other.deviceId) return false
        if (name != other.name) return false
        if (category != other.category) return false
        if (frequency != other.frequency) return false
        if (!pattern.contentEquals(other.pattern)) return false
        if (description != other.description) return false
        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + deviceId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + frequency
        result = 31 * result + pattern.contentHashCode()
        result = 31 * result + description.hashCode()
        return result
    }
}

enum class CommandCategory {
    POWER,
    VOLUME,
    CHANNEL,
    NAVIGATION,
    INPUT,
    PLAYBACK,
    TEMPERATURE,
    FAN_SPEED,
    MODE,
    CUSTOM
}
