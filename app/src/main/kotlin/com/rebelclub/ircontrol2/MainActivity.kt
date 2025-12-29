package com.rebelclub.ircontrol2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rebelclub.ircontrol2.ui.screens.HomeScreen
import com.rebelclub.ircontrol2.ui.screens.ControlScreen
import com.rebelclub.ircontrol2.ui.screens.AddDeviceScreen
import com.rebelclub.ircontrol2.ui.theme.IRControl2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IRControl2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onAddDevice = { navController.navigate("add_device") },
                onEditDevice = { deviceId ->
                    navController.navigate("edit_device/$deviceId")
                },
                onDeviceClick = { deviceId ->
                    navController.navigate("control/$deviceId")
                }
            )
        }

        composable("add_device") {
            AddDeviceScreen(
                device = null,
                onBackClick = { navController.navigateUp() },
                onSaveClick = { navController.navigateUp() }
            )
        }

        composable("edit_device/{deviceId}") { backStackEntry ->
            val deviceId = backStackEntry.arguments?.getString("deviceId")
            AddDeviceScreen(
                device = null,
                onBackClick = { navController.navigateUp() },
                onSaveClick = { navController.navigateUp() }
            )
        }

        composable("control/{deviceId}") { backStackEntry ->
            val deviceId = backStackEntry.arguments?.getString("deviceId")
            ControlScreen(
                deviceId = deviceId ?: "",
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}
