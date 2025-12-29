package com.rebelclub.ircontrol2.data.repository

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import com.rebelclub.ircontrol2.core.model.IRDevice
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException
import java.util.*
import javax.inject.Inject

class IRDeviceRepositoryImpl @Inject constructor(
    private val bluetoothAdapter: BluetoothAdapter?
) : IRDeviceRepository {

    private val _irDevices = MutableStateFlow<List<IRDevice>>(emptyList())
    private var bluetoothSocket: BluetoothSocket? = null
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

    override fun getIRDevices(): Flow<List<IRDevice>> {
        scanForDevices()
        return _irDevices.asStateFlow()
    }

    override suspend fun connectToDevice(deviceAddress: String): Result<Unit> {
        return try {
            val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(deviceAddress)
            if (device == null) {
                return Result.failure(Exception("Device not found"))
            }

            bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid)
            bluetoothAdapter?.cancelDiscovery()
            bluetoothSocket?.connect()

            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun sendIRSignal(signal: String): Result<Unit> {
        return try {
            val socket = bluetoothSocket
            if (socket == null || !socket.isConnected) {
                return Result.failure(Exception("Not connected to IR device"))
            }

            val outputStream = socket.outputStream
            outputStream.write(signal.toByteArray())
            outputStream.flush()

            Result.success(Unit)
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun disconnectDevice() {
        try {
            bluetoothSocket?.close()
            bluetoothSocket = null
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun scanForDevices() {
        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        val devices = pairedDevices?.map { device ->
            IRDevice(
                name = device.name ?: "Unknown",
                address = device.address,
                isConnected = false
            )
        } ?: emptyList()

        _irDevices.value = devices
    }
}