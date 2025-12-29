package com.rebelclub.ircontrol2.domain.usecase.device

import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDeviceByIdUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    operator fun invoke(deviceId: String): Flow<Device?> {
        return deviceRepository.getDeviceById(deviceId)
    }
}
