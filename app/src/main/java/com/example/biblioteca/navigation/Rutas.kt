
package com.example.biblioteca.navigation

object Rutas {

    const val INICIO = "inicio"
    const val LIBROS = "libros"
    const val DETALLE = "detalle/{libroId}"
    const val CONFIRMACION = "confirmacion/{libroId}"
    const val MIS_RESERVAS = "mis-reservas"
    const val DETALLE_RESERVA = "detalle-reserva/{reservaId}"

    fun detalleLibro(id: String) = "detalle/$id"

    fun confirmacion(id: String) = "confirmacion/$id"

    fun detalleReserva(id: String) = "detalle-reserva/$id"

    const val RENOVAR_RESERVA = "renovar-reserva/{reservaId}"

    const val RENOVACION_EXITOSA =
        "renovacion-exitosa/{reservaId}"

    fun renovarReserva(id: String) =
        "renovar-reserva/$id"

    fun renovacionExitosa(id: String) =
        "renovacion-exitosa/$id"

}
