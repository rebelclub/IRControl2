package com.rebelclub.ircontrol2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rebelclub.ircontrol2.data.local.dao.CommandDao
import com.rebelclub.ircontrol2.data.local.dao.DeviceDao
import com.rebelclub.ircontrol2.data.local.entity.CommandEntity
import com.rebelclub.ircontrol2.data.local.entity.DeviceEntity

@Database(
    entities = [DeviceEntity::class, CommandEntity::class],
    version = 1,
    exportSchema = false
)
abstract class IRControlDatabase : RoomDatabase() {
    abstract fun deviceDao(): DeviceDao
    abstract fun commandDao(): CommandDao
}