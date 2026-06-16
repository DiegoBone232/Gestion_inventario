package com.example.gestion_inventario.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.gestion_inventario.model.Producto

class StockViewModel : ViewModel() {

    // Lista reactiva con 6 productos precargados
    private val _productos = mutableStateListOf(
        Producto(1, "Laptop", "Laptop de alta gama", 1200.0, 10),
        Producto(2, "Mouse", "Mouse inalámbrico", 25.0, 50),
        Producto(3, "Teclado", "Teclado mecánico RGB", 80.0, 4),
        Producto(4, "Monitor", "Monitor 4K 27 pulgadas", 350.0, 8),
        Producto(5, "Auriculares", "Auriculares con cancelación de ruido", 150.0, 3),
        Producto(6, "Webcam", "Webcam Full HD", 60.0, 15)
    )

    val productos: List<Producto> get() = _productos

    /**
     * Obtiene un producto por su ID.
     */
    fun obtenerProducto(id: Int): Producto? {
        return _productos.find { it.id == id }
    }

    /**
     * Actualiza el stock de un producto específico.
     */
    fun actualizarStock(id: Int, nuevaCantidad: Int) {
        val index = _productos.indexOfFirst { it.id == id }
        if (index != -1) {
            // En Compose, para que el cambio en un objeto dentro de la lista sea reactivo,
            // solemos reemplazar el objeto o usar un objeto reactivo.
            // Dado que Producto es un data class con var stockActual, 
            // simplemente modificar el valor NO disparará la recomposición si la lista no cambia.
            // Sin embargo, para cumplir estrictamente con MVVM y reactividad en Compose:
            val productoActualizado = _productos[index].copy(stockActual = nuevaCantidad)
            _productos[index] = productoActualizado
        }
    }

    /**
     * Calcula el valor total de todo el inventario (precio * stock).
     */
    fun calcularValorTotalInventario(): Double {
        return _productos.sumOf { it.precio * it.stockActual }
    }

    /**
     * Obtiene los productos con stock bajo (por ejemplo, menos de 5 unidades).
     */
    fun obtenerProductosEnRiesgo(): List<Producto> {
        return _productos.filter { it.stockActual < 5 }
    }

    /**
     * Obtiene el conteo de productos que tienen stock en cero.
     */
    fun obtenerConteoStockCero(): Int {
        return _productos.count { it.stockActual == 0 }
    }
}
