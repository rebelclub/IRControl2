package com.rebelclub.ircontrol2.data.local.dao

import androidx.room.*
import com.rebelclub.ircontrol2.data.local.entity.IRCommandEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IRCommandDao {
    
    @Query("SELECT * FROM ir_commands WHERE deviceId = :deviceId ORDER BY category, name ASC")
    fun getCommandsByDevice(deviceId: String): Flow<List<IRCommandEntity>>
    
    @Query("SELECT * FROM ir_commands WHERE id = :commandId")
    suspend fun getCommandById(commandId: String): IRCommandEntity?
    
    @Query("SELECT * FROM ir_commands WHERE deviceId = :deviceId AND category = :category ORDER BY name ASC")
    fun getCommandsByCategory(deviceId: String, category: String): Flow<List<IRCommandEntity>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommand(command: IRCommandEntity)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCommands(commands: List<IRCommandEntity>)
    
    @Update
    suspend fun updateCommand(command: IRCommandEntity)
    
    @Delete
    suspend fun deleteCommand(command: IRCommandEntity)
    
    @Query("DELETE FROM ir_commands WHERE id = :commandId")
    suspend fun deleteCommandById(commandId: String)
    
    @Query("DELETE FROM ir_commands WHERE deviceId = :deviceId")
    suspend fun deleteCommandsByDevice(deviceId: String)
}
