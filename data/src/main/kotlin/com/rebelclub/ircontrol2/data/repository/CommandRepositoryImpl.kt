package com.rebelclub.ircontrol2.data.repository

import com.rebelclub.ircontrol2.core.model.Command
import com.rebelclub.ircontrol2.data.local.dao.CommandDao
import com.rebelclub.ircontrol2.data.local.entity.toDomain
import com.rebelclub.ircontrol2.data.local.entity.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommandRepositoryImpl @Inject constructor(
    private val commandDao: CommandDao,
    private val irDeviceRepository: IRDeviceRepository
) : CommandRepository {

    override fun getAllCommands(): Flow<List<Command>> {
        return commandDao.getAllCommands().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getCommandsByDeviceId(deviceId: String): Flow<List<Command>> {
        return commandDao.getCommandsByDeviceId(deviceId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun addCommand(command: Command) {
        commandDao.insertCommand(command.toEntity())
    }

    override suspend fun updateCommand(command: Command) {
        commandDao.updateCommand(command.toEntity())
    }

    override suspend fun deleteCommand(commandId: String) {
        commandDao.deleteCommand(commandId)
    }

    override suspend fun executeCommand(commandId: String): Result<Unit> {
        return try {
            val command = commandDao.getCommandById(commandId)
            if (command != null) {
                irDeviceRepository.sendIRSignal(command.irSignal)
            } else {
                Result.failure(Exception("Command not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}