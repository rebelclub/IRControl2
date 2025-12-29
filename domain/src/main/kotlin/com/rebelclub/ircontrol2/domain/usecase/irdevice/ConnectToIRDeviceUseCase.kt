package com.rebelclub.ircontrol2.domain.usecase.irdevice

import com.rebelclub.ircontrol2.data.repository.IRDeviceRepository
import javax.inject.Inject

class ConnectToIRDeviceUseCase @Inject constructor(
    private val irDeviceRepository: IRDeviceRepository
) {
    suspend operator fun invoke(deviceAddress: String): Result<Unit> {
        return irDeviceRepository.connectToDevice(deviceAddress)
    }
}