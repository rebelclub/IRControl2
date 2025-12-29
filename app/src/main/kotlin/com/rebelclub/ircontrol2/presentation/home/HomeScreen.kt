package com.rebelclub.ircontrol2.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rebelclub.ircontrol2.core.model.Device

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDeviceClick: (String) -> Unit,
    onAddDeviceClick: () -> Unit,
    onIRDevicesClick: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("IR Control") },
                actions = {
                    IconButton(onClick = onIRDevicesClick) {
                        Icon(Icons.Default.Settings, contentDescription = "IR Devices")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddDeviceClick) {
                Icon(Icons.Default.Add, contentDescription = "Add Device")
            }
        }
    ) { paddingValues ->
        if (uiState.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (uiState.favoriteDevices.isNotEmpty()) {
                    item {
                        Text(
                            text = "Favorites",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                    item {
                        LazyRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(uiState.favoriteDevices) { device ->
                                DeviceCard(
                                    device = device,
                                    onClick = { onDeviceClick(device.id) }
                                )
                            }
                        }
                    }
                    item { Spacer(modifier = Modifier.height(16.dp)) }
                }

                item {
                    Text(
                        text = "All Devices",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                items(uiState.devices) { device ->
                    DeviceCard(
                        device = device,
                        onClick = { onDeviceClick(device.id) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeviceCard(
    device: Device,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = device.name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "${device.brand} ${device.model}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
