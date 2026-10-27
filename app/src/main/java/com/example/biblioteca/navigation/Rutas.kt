
package com.example.biblioteca.navigation

object Rutas {
    const val INICIO = "inicio"
    const val LIBROS = "libros"
    const val DETALLE = "detalle/{libroId}"
    const val CONFIRMACION = "confirmacion/{libroId}"

    fun detalleLibro(id: String) = "detalle/$id"

    fun confirmacion(id: String) = "confirmacion/$id"
}
