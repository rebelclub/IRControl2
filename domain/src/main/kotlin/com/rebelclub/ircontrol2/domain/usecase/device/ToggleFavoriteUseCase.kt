package com.rebelclub.ircontrol2.domain.usecase.device

import com.rebelclub.ircontrol2.data.repository.DeviceRepository
import javax.inject.Inject

class ToggleFavoriteUseCase @Inject constructor(
    private val deviceRepository: DeviceRepository
) {
    suspend operator fun invoke(deviceId: String, isFavorite: Boolean) {
        deviceRepository.toggleFavorite(deviceId, isFavorite)
    }
}