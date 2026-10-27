
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.biblioteca.data.Reserva

@Composable
fun RenovacionExitosaScreen(
    reserva: Reserva?,
    onVolverReservas: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "¡Reserva renovada!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Nuevo vencimiento: " +
                    (reserva?.fechaVencimiento ?: "No disponible")
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onVolverReservas
        ) {
            Text("Volver a Mis Reservas")
        }
    }
}
