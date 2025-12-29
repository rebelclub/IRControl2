package com.rebelclub.ircontrol2.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rebelclub.ircontrol2.core.model.Device
import com.rebelclub.ircontrol2.domain.usecase.device.GetAllDevicesUseCase
import com.rebelclub.ircontrol2.domain.usecase.device.GetFavoriteDevicesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllDevicesUseCase: GetAllDevicesUseCase,
    private val getFavoriteDevicesUseCase: GetFavoriteDevicesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadDevices()
    }

    private fun loadDevices() {
        viewModelScope.launch {
            combine(
                getAllDevicesUseCase(),
                getFavoriteDevicesUseCase()
            ) { allDevices, favoriteDevices ->
                HomeUiState(
                    devices = allDevices,
                    favoriteDevices = favoriteDevices,
                    isLoading = false
                )
            }.collect { state ->
                _uiState.value = state
            }
        }
    }
}

data class HomeUiState(
    val devices: List<Device> = emptyList(),
    val favoriteDevices: List<Device> = emptyList(),
    val isLoading: Boolean = true
)