package com.rebelclub.ircontrol2.domain.usecase.command

import com.rebelclub.ircontrol2.core.model.Command
import com.rebelclub.ircontrol2.data.repository.CommandRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCommandsByDeviceIdUseCase @Inject constructor(
    private val commandRepository: CommandRepository
) {
    operator fun invoke(deviceId: String): Flow<List<Command>> {
        return commandRepository.getCommandsByDeviceId(deviceId)
    }
}