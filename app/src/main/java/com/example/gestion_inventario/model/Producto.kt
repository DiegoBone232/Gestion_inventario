package com.example.gestion_inventario.model

/**
 * Data class que representa un producto en el inventario.
 */
data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var stockActual: Int
)
