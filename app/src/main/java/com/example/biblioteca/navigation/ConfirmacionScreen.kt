
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ConfirmacionScreen(
    libroId: String,
    onVolverInicio: () -> Unit
) {
    val nombreLibro = when (libroId) {
        "1" -> "Clean Code"
        "2" -> "Kotlin para Android"
        "3" -> "Arquitectura de Software"
        "4" -> "Jetpack Compose"
        else -> "Libro desconocido"
    }

    val fecha = LocalDate.now().format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "✅",
            fontSize = 90.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "¡Reserva Exitosa!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {
                Text("Libro: $nombreLibro")
                Text("Fecha: $fecha")
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onVolverInicio,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver al Inicio")
        }
    }
}
