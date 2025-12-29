package com.rebelclub.ircontrol2.presentation.irdevices

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebelclub.ircontrol2.core.model.IRDevice
import com.rebelclub.ircontrol2.domain.usecase.irdevice.ConnectToIRDeviceUseCase
import com.rebelclub.ircontrol2.domain.usecase.irdevice.GetIRDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IRDevicesViewModel @Inject constructor(
    private val getIRDevicesUseCase: GetIRDevicesUseCase,
    private val connectToIRDeviceUseCase: ConnectToIRDeviceUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(IRDevicesUiState())
    val uiState: StateFlow<IRDevicesUiState> = _uiState.asStateFlow()

    init {
        loadIRDevices()
    }

    private fun loadIRDevices() {
        viewModelScope.launch {
            getIRDevicesUseCase().collect { devices ->
                _uiState.update { 
                    it.copy(
                        devices = devices,
                        isLoading = false
                    )
                }
            }
        }
    }

    fun connectToDevice(deviceAddress: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isConnecting = true) }
            
            val result = connectToIRDeviceUseCase(deviceAddress)
            
            _uiState.update { 
                it.copy(
                    isConnecting = false,
                    isConnected = result.isSuccess,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }
}

data class IRDevicesUiState(
    val devices: List<IRDevice> = emptyList(),
    val isLoading: Boolean = true,
    val isConnecting: Boolean = false,
    val isConnected: Boolean = false,
    val error: String? = null
)