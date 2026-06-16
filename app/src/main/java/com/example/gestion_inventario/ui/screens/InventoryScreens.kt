package com.example.gestion_inventario.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gestion_inventario.model.Producto
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen2(
    workerName: String,
    viewModel: StockViewModel,
    onProductClick: (Int) -> Unit,
    onNavigateToScreen4: () -> Unit
) {
    var showOnlyCritical by remember { mutableStateOf(false) }
    
    val productList = if (showOnlyCritical) {
        viewModel.obtenerProductosEnRiesgo()
    } else {
        viewModel.productos
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onNavigateToScreen4) {
                Icon(Icons.Default.Add, contentDescription = "Ir a Pantalla 4")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text(
                text = "Operario: $workerName",
                style = MaterialTheme.typography.titleMedium
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = { showOnlyCritical = false },
                    modifier = Modifier.weight(1f),
                    colors = if (!showOnlyCritical) ButtonDefaults.buttonColors() else ButtonDefaults.filledTonalButtonColors()
                ) {
                    Text("Ver Todo")
                }
                Button(
                    onClick = { showOnlyCritical = true },
                    modifier = Modifier.weight(1f),
                    colors = if (showOnlyCritical) ButtonDefaults.buttonColors() else ButtonDefaults.filledTonalButtonColors()
                ) {
                    Text("Stock Crítico")
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(productList) { producto ->
                    ProductCard(
                        producto = producto,
                        onClick = { onProductClick(producto.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    producto: Producto,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = producto.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "Precio Unitario: $${producto.precio}", style = MaterialTheme.typography.bodyMedium)
            
            val stockColor = if (producto.stockActual < 5) Color.Red else Color.Unspecified
            Text(
                text = "Stock Actual: ${producto.stockActual}",
                style = MaterialTheme.typography.bodyLarge,
                color = stockColor
            )
        }
    }
}

@Composable
fun Screen3(productId: Int, viewModel: StockViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla 3 - Producto ID: $productId", style = MaterialTheme.typography.titleLarge)
    }
}

@Composable
fun Screen4(viewModel: StockViewModel) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla 4", style = MaterialTheme.typography.titleLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun Screen2Preview() {
    MaterialTheme {
        Screen2(
            workerName = "Diego",
            viewModel = StockViewModel(),
            onProductClick = {},
            onNavigateToScreen4 = {}
        )
    }
}
