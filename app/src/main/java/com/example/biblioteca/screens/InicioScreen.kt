
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.OutlinedButton
@Composable
fun InicioScreen(
    onVerLibros: () -> Unit,
    onMisReservas: () -> Unit
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
            text = "📚",
            fontSize = 80.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "¡Bienvenido a tu Biblioteca!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Descubre, aprende y reserva tus libros favoritos.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onVerLibros
        ) {
            Text("Ver Libros")
        }
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = onMisReservas
        ) {
            Text("Mis Reservas")
        }
    }
}
