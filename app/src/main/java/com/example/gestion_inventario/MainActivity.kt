package com.example.gestion_inventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gestion_inventario.ui.screens.LoginScreen
import com.example.gestion_inventario.ui.screens.Screen2
import com.example.gestion_inventario.ui.screens.Screen3
import com.example.gestion_inventario.ui.screens.Screen4
import com.example.gestion_inventario.ui.theme.Gestion_inventarioTheme
import com.example.gestion_inventario.viewmodel.StockViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Gestion_inventarioTheme {
                InventoryApp()
            }
        }
    }
}

@Composable
fun InventoryApp() {
    val navController = rememberNavController()
    // Instancia única de StockViewModel compartida por todas las pantallas
    val stockViewModel: StockViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("login") {
                LoginScreen(
                    onLoginSuccess = { name ->
                        navController.navigate("screen2/$name")
                    }
                )
            }
            composable(
                route = "screen2/{workerName}",
                arguments = listOf(navArgument("workerName") { type = NavType.StringType })
            ) { backStackEntry ->
                val name = backStackEntry.arguments?.getString("workerName") ?: ""
                Screen2(workerName = name, viewModel = stockViewModel)
            }
            composable("screen3") {
                Screen3(viewModel = stockViewModel)
            }
            composable("screen4") {
                Screen4(viewModel = stockViewModel)
            }
        }
    }
}
