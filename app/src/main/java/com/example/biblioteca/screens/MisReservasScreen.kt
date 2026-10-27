
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.biblioteca.data.Reserva

@Composable


fun MisReservasScreen(
    reservas: List<Reserva>,
    onVolver: () -> Unit,
    onVerReserva: (String) -> Unit
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp)
    ) {

        TextButton(
            onClick = onVolver
        ) {
            Text("← Volver al Inicio")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Mis Reservas",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (reservas.isEmpty()) {

            Text("Aún no tienes libros reservados.")

        } else {

            LazyColumn(
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = reservas,
                    key = { it.id }
                ) { reserva ->

                    val nombreLibro = when (reserva.libroId) {
                        "1" -> "Clean Code"
                        "2" -> "Kotlin para Android"
                        "3" -> "Arquitectura de Software"
                        "4" -> "Jetpack Compose"
                        else -> "Libro desconocido"
                    }

                    Card(
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement =
                                Arrangement.spacedBy(8.dp)
                        ) {

                            Text(
                                text = nombreLibro,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = "Fecha: ${reserva.fechaReserva}"
                            )

                            Text(
                                text = "Vencimiento: ${reserva.fechaVencimiento}"
                            )

                            Text(
                                text = "Estado: Activa"
                            )

                            Button(
                                onClick = {
                                    onVerReserva(reserva.id)
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Ver Reserva")
                            }

                        }
                    }
                }
            }
        }
    }
}
