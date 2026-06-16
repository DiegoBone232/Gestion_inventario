package com.example.gestion_inventario.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestion_inventario.viewmodel.StockViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: (String) -> Unit
) {
    var workerName by remember { mutableStateOf("") }
    val isButtonEnabled = workerName.length >= 3

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido a StockPro",
            style = MaterialTheme.typography.headlineMedium
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        TextField(
            value = workerName,
            onValueChange = { workerName = it },
            label = { Text("Nombre del trabajador") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = { onLoginSuccess(workerName) },
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ingresar al Sistema")
        }
    }
}

@Composable
fun Screen2(workerName: String, viewModel: StockViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla 2 - Operario: $workerName", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun Screen3(viewModel: StockViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla 3", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun Screen4(viewModel: StockViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla 4", style = MaterialTheme.typography.titleLarge)
    }
}
