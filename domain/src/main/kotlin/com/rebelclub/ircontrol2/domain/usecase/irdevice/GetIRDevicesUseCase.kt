package com.rebelclub.ircontrol2.domain.usecase.irdevice

import com.rebelclub.ircontrol2.core.model.IRDevice
import com.rebelclub.ircontrol2.data.repository.IRDeviceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIRDevicesUseCase @Inject constructor(
    private val irDeviceRepository: IRDeviceRepository
) {
    operator fun invoke(): Flow<List<IRDevice>> {
        return irDeviceRepository.getIRDevices()
    }
}