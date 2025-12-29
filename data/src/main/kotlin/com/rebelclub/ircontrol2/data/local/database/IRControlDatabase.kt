package com.rebelclub.ircontrol2.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rebelclub.ircontrol2.data.local.dao.DeviceDao
import com.rebelclub.ircontrol2.data.local.dao.IRCommandDao
import com.rebelclub.ircontrol2.data.local.entity.DeviceEntity
import com.rebelclub.ircontrol2.data.local.entity.IRCommandEntity

@Database(
    entities = [
        DeviceEntity::class,
        IRCommandEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class IRControlDatabase : RoomDatabase() {
    
    abstract fun deviceDao(): DeviceDao
    abstract fun irCommandDao(): IRCommandDao
    
    companion object {
        const val DATABASE_NAME = "ir_control_database"
    }
}
