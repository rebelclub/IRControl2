package com.rebelclub.ircontrol2.domain.usecase.command

import com.rebelclub.ircontrol2.core.model.Command
import com.rebelclub.ircontrol2.data.repository.CommandRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCommandsUseCase @Inject constructor(
    private val commandRepository: CommandRepository
) {
    operator fun invoke(): Flow<List<Command>> {
        return commandRepository.getAllCommands()
    }
}