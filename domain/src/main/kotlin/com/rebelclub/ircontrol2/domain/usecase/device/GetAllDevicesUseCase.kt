package com.rebelclub.ircontrol2.domain.usecase.device

import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDevicesUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    operator fun invoke(): Flow<List<Device>> {
        return deviceRepository.getAllDevices()
    }
}
