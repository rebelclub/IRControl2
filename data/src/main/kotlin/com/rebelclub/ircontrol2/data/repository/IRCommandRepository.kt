package com.rebelclub.ircontrol2.data.repository

import com.rebelclub.ircontrol2.core.model.IRCommand
import com.rebelclub.ircontrol2.data.local.dao.IRCommandDao
import com.rebelclub.ircontrol2.data.local.entity.toDomain
import com.rebelclub.ircontrol2.data.local.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface IRCommandRepository {
    fun getCommandsByDevice(deviceId: String): Flow<List<IRCommand>>
    suspend fun getCommandById(commandId: String): IRCommand?
    fun getCommandsByCategory(deviceId: String, category: String): Flow<List<IRCommand>>
    suspend fun insertCommand(command: IRCommand)
    suspend fun insertCommands(commands: List<IRCommand>)
    suspend fun updateCommand(command: IRCommand)
    suspend fun deleteCommand(command: IRCommand)
    suspend fun deleteCommandById(commandId: String)
    suspend fun deleteCommandsByDevice(deviceId: String)
}

@Singleton
class IRCommandRepositoryImpl @Inject constructor(
    private val irCommandDao: IRCommandDao
) : IRCommandRepository {
    
    override fun getCommandsByDevice(deviceId: String): Flow<List<IRCommand>> {
        return irCommandDao.getCommandsByDevice(deviceId).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun getCommandById(commandId: String): IRCommand? {
        return irCommandDao.getCommandById(commandId)?.toDomain()
    }
    
    override fun getCommandsByCategory(deviceId: String, category: String): Flow<List<IRCommand>> {
        return irCommandDao.getCommandsByCategory(deviceId, category).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override suspend fun insertCommand(command: IRCommand) {
        irCommandDao.insertCommand(command.toEntity())
    }
    
    override suspend fun insertCommands(commands: List<IRCommand>) {
        irCommandDao.insertCommands(commands.map { it.toEntity() })
    }
    
    override suspend fun updateCommand(command: IRCommand) {
        irCommandDao.updateCommand(command.toEntity())
    }
    
    override suspend fun deleteCommand(command: IRCommand) {
        irCommandDao.deleteCommand(command.toEntity())
    }
    
    override suspend fun deleteCommandById(commandId: String) {
        irCommandDao.deleteCommandById(commandId)
    }
    
    override suspend fun deleteCommandsByDevice(deviceId: String) {
        irCommandDao.deleteCommandsByDevice(deviceId)
    }
}
