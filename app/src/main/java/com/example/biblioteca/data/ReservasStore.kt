
package com.example.biblioteca.data

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate
import java.util.UUID

data class Reserva(
    val id: String,
    val libroId: String,
    val fechaReserva: String,
    val fechaVencimiento: String
)

class ReservasStore(context: Context) {

    fun renovarConFecha(
        reservaId: String,
        nuevaFecha: LocalDate
    ): Boolean {
        val indice = lista.indexOfFirst {
            it.id == reservaId
        }

        if (indice == -1) return false

        val actual = lista[indice]

        val vencimiento = runCatching {
            LocalDate.parse(actual.fechaVencimiento)
        }.getOrNull() ?: return false

        if (nuevaFecha <= maxOf(vencimiento, LocalDate.now())) {
            return false
        }

        lista[indice] = actual.copy(
            fechaVencimiento = nuevaFecha.toString()
        )

        guardar()
        return true
    }


    private val preferencias = context.getSharedPreferences(
        "biblioteca_reservas",
        Context.MODE_PRIVATE
    )

    private val lista = mutableStateListOf<Reserva>()

    val reservas: List<Reserva>
        get() = lista

    init {
        cargar()
    }

    fun reservar(libroId: String): Boolean {

        if (libroId !in setOf("1", "2", "3", "4")) {
            return false
        }

        if (lista.any { it.libroId == libroId }) {
            return false
        }

        val hoy = LocalDate.now()

        val nuevaReserva = Reserva(
            id = UUID.randomUUID().toString(),
            libroId = libroId,
            fechaReserva = hoy.toString(),
            fechaVencimiento = hoy.plusDays(7).toString()
        )

        lista.add(nuevaReserva)

        guardar()

        return true
    }

    fun cancelar(reservaId: String): Boolean {
        val eliminada = lista.removeAll {
            it.id == reservaId
        }

        if (eliminada) {
            guardar()
        }

        return eliminada
    }

    fun renovar(reservaId: String): Boolean {
        val indice = lista.indexOfFirst {
            it.id == reservaId
        }

        if (indice == -1) return false

        val actual = lista[indice]

        val vencimiento = runCatching {
            LocalDate.parse(actual.fechaVencimiento)
        }.getOrNull() ?: return false

        val fechaBase = maxOf(
            vencimiento,
            LocalDate.now()
        )

        lista[indice] = actual.copy(
            fechaVencimiento = fechaBase.plusDays(7).toString()
        )

        guardar()

        return true
    }

    private fun guardar() {

        val datos = JSONArray()

        lista.forEach { reserva ->

            val objeto = JSONObject().apply {
                put("id", reserva.id)
                put("libroId", reserva.libroId)
                put("fechaReserva", reserva.fechaReserva)
                put(
                    "fechaVencimiento",
                    reserva.fechaVencimiento
                )
            }

            datos.put(objeto)
        }

        preferencias.edit()
            .putString("reservas", datos.toString())
            .apply()
    }

    private fun cargar() {

        val contenido = preferencias.getString(
            "reservas",
            "[]"
        ) ?: "[]"

        val datos = runCatching {
            JSONArray(contenido)
        }.getOrNull() ?: return

        for (i in 0 until datos.length()) {

            val item = datos.optJSONObject(i) ?: continue

            val id = item.optString("id")
            val libroId = item.optString("libroId")

            if (id.isBlank() || libroId.isBlank()) {
                continue
            }

            lista.add(
                Reserva(
                    id = id,
                    libroId = libroId,
                    fechaReserva = item.optString("fechaReserva"),
                    fechaVencimiento = item.optString(
                        "fechaVencimiento"
                    )
                )
            )
        }
    }
}
