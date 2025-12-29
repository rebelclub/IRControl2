package com.rebelclub.ircontrol2.domain.usecase.command

import com.rebelclub.ircontrol2.data.repository.CommandRepository
import javax.inject.Inject

class ExecuteCommandUseCase @Inject constructor(
    private val commandRepository: CommandRepository
) {
    suspend operator fun invoke(commandId: String): Result<Unit> {
        return commandRepository.executeCommand(commandId)
    }
}