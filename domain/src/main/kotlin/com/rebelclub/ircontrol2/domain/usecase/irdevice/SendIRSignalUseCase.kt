package com.rebelclub.ircontrol2.domain.usecase.irdevice

import com.rebelclub.ircontrol2.data.repository.IRDeviceRepository
import javax.inject.Inject

class SendIRSignalUseCase @Inject constructor(
    private val irDeviceRepository: IRDeviceRepository
) {
    suspend operator fun invoke(signal: String): Result<Unit> {
        return irDeviceRepository.sendIRSignal(signal)
    }
}