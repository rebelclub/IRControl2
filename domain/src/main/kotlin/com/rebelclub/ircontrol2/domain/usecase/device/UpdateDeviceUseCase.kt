package com.rebelclub.ircontrol2.domain.usecase.device

import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import javax.inject.Inject

class UpdateDeviceUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke(device: Device) {
        deviceRepository.updateDevice(device)
    }
}
