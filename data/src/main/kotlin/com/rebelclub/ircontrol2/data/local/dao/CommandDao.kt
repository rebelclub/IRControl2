package com.rebelclub.ircontrol2.data.local.dao

import androidx.room.*
import com.rebelclub.ircontrol2.data.local.entity.CommandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommandDao {
    @Query("SELECT * FROM commands ORDER BY createdAt DESC")
    fun getAllCommands(): Flow<List<CommandEntity>>

    @Query("SELECT * FROM commands WHERE deviceId = :deviceId ORDER BY createdAt DESC")
    fun getCommandsByDeviceId(deviceId: String): Flow<List<CommandEntity>>

    @Query("SELECT * FROM commands WHERE id = :commandId")
    suspend fun getCommandById(commandId: String): CommandEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommand(command: CommandEntity)

    @Update
    suspend fun updateCommand(command: CommandEntity)

    @Query("DELETE FROM commands WHERE id = :commandId")
    suspend fun deleteCommand(commandId: String)

    @Query("DELETE FROM commands WHERE deviceId = :deviceId")
    suspend fun deleteCommandsByDeviceId(deviceId: String)
}