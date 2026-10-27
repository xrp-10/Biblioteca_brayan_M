
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.biblioteca.data.Reserva

@Composable
fun DetalleReservaScreen(
    reserva: Reserva?,
    onVolver: () -> Unit,
    onRenovar: () -> Unit,
    onCancelar: () -> Unit
) {
    var confirmarCancelacion by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextButton(onClick = onVolver) {
            Text("← Volver a Mis Reservas")
        }

        Text(
            text = "Detalle de Reserva",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        if (reserva == null) {
            Text("Reserva no encontrada.")
        } else {
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
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {
                    Text("Libro: $nombreLibro")
                    Text("Fecha: ${reserva.fechaReserva}")
                    Text("Vencimiento: ${reserva.fechaVencimiento}")
                    Text("Código: ${reserva.id}")
                    Text("Estado: Activa")
                }
            }

            Button(
                onClick = onRenovar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Renovar Reserva")
            }

            OutlinedButton(
                onClick = { confirmarCancelacion = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar Reserva")
            }
        }
    }

    if (confirmarCancelacion) {
        AlertDialog(
            onDismissRequest = {
                confirmarCancelacion = false
            },
            title = {
                Text("Cancelar reserva")
            },
            text = {
                Text("¿Estás seguro de cancelar esta reserva?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        confirmarCancelacion = false
                        onCancelar()
                    }
                ) {
                    Text("Sí, cancelar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        confirmarCancelacion = false
                    }
                ) {
                    Text("Volver")
                }
            }
        )
    }
}
