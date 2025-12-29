package com.rebelclub.ircontrol2.presentation.adddevice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.core.model.DeviceType
import com.rebelclub.ircontrol2.domain.usecase.device.AddDeviceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddDeviceViewModel @Inject constructor(
    private val addDeviceUseCase: AddDeviceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddDeviceUiState())
    val uiState: StateFlow<AddDeviceUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateType(type: DeviceType) {
        _uiState.update { it.copy(type = type) }
    }

    fun updateBrand(brand: String) {
        _uiState.update { it.copy(brand = brand) }
    }

    fun updateModel(model: String) {
        _uiState.update { it.copy(model = model) }
    }

    fun saveDevice() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }
            
            val device = Device(
                id = UUID.randomUUID().toString(),
                name = _uiState.value.name,
                type = _uiState.value.type,
                brand = _uiState.value.brand,
                model = _uiState.value.model,
                iconResId = 0,
                isFavorite = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis()
            )
            
            try {
                addDeviceUseCase(device)
                _uiState.update { it.copy(isSaving = false, isSaved = true) }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isSaving = false, 
                        error = e.message ?: "Unknown error"
                    ) 
                }
            }
        }
    }
}

data class AddDeviceUiState(
    val name: String = "",
    val type: DeviceType = DeviceType.TV,
    val brand: String = "",
    val model: String = "",
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
    val error: String? = null
)