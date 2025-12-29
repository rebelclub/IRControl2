package com.rebelclub.ircontrol2.presentation.devicedetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebelclub.ircontrol2.core.model.Command
import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.domain.usecase.command.ExecuteCommandUseCase
import com.rebelclub.ircontrol2.domain.usecase.command.GetCommandsByDeviceIdUseCase
import com.rebelclub.ircontrol2.domain.usecase.device.GetDeviceByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getDeviceByIdUseCase: GetDeviceByIdUseCase,
    private val getCommandsByDeviceIdUseCase: GetCommandsByDeviceIdUseCase,
    private val executeCommandUseCase: ExecuteCommandUseCase
) : ViewModel() {

    private val deviceId: String = checkNotNull(savedStateHandle["deviceId"])

    private val _uiState = MutableStateFlow(DeviceDetailUiState())
    val uiState: StateFlow<DeviceDetailUiState> = _uiState.asStateFlow()

    init {
        loadDeviceDetails()
    }

    private fun loadDeviceDetails() {
        viewModelScope.launch {
            combine(
                getDeviceByIdUseCase(deviceId),
                getCommandsByDeviceIdUseCase(deviceId)
            ) { device, commands ->
                DeviceDetailUiState(
                    device = device,
                    commands = commands,
                    isLoading = false
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }

    fun executeCommand(commandId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isExecuting = true) }
            val result = executeCommandUseCase(commandId)
            _uiState.update { 
                it.copy(
                    isExecuting = false,
                    error = result.exceptionOrNull()?.message
                )
            }
        }
    }
}

data class DeviceDetailUiState(
    val device: Device? = null,
    val commands: List<Command> = emptyList(),
    val isLoading: Boolean = true,
    val isExecuting: Boolean = false,
    val error: String? = null
)