package com.rebelclub.ircontrol2.domain.usecase.command

import com.rebelclub.ircontrol2.core.model.Command
import com.rebelclub.ircontrol2.data.repository.CommandRepository
import javax.inject.Inject

class UpdateCommandUseCase @Inject constructor(
    private val commandRepository: CommandRepository
) {
    suspend operator fun invoke(command: Command) {
        commandRepository.updateCommand(command)
    }
}