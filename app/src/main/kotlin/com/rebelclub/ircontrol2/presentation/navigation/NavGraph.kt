package com.rebelclub.ircontrol2.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rebelclub.ircontrol2.presentation.adddevice.AddDeviceScreen
import com.rebelclub.ircontrol2.presentation.devicedetail.DeviceDetailScreen
import com.rebelclub.ircontrol2.presentation.home.HomeScreen
import com.rebelclub.ircontrol2.presentation.irdevices.IRDevicesScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onDeviceClick = { deviceId ->
                    navController.navigate(Screen.DeviceDetail.createRoute(deviceId))
                },
                onAddDeviceClick = {
                    navController.navigate(Screen.AddDevice.route)
                },
                onIRDevicesClick = {
                    navController.navigate(Screen.IRDevices.route)
                }
            )
        }

        composable(
            route = Screen.DeviceDetail.route,
            arguments = listOf(navArgument("deviceId") { type = NavType.StringType })
        ) {
            DeviceDetailScreen(
                onBackClick = { navController.popBackStack() }
            )
        }

        composable(Screen.AddDevice.route) {
            AddDeviceScreen(
                onBackClick = { navController.popBackStack() },
                onDeviceSaved = { navController.popBackStack() }
            )
        }

        composable(Screen.IRDevices.route) {
            IRDevicesScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object DeviceDetail : Screen("device/{deviceId}") {
        fun createRoute(deviceId: String) = "device/$deviceId"
    }
    object AddDevice : Screen("add_device")
    object IRDevices : Screen("ir_devices")
}
