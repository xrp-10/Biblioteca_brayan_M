
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private data class InformacionLibro(
    val nombre: String,
    val autor: String,
    val categoria: String,
    val descripcion: String
)

@Composable
fun DetalleLibroScreen(
    libroId: String,
    onVolver: () -> Unit,
    onReservar: () -> Unit
) {
    val libro = when (libroId) {
        "1" -> InformacionLibro(
            "Clean Code",
            "Robert C. Martin",
            "Programación",
            "Una guía práctica para escribir código limpio, legible y mantenible."
        )
        "2" -> InformacionLibro(
            "Kotlin para Android",
            "No especificado",
            "Android",
            "Aprende a desarrollar aplicaciones Android utilizando Kotlin."
        )
        "3" -> InformacionLibro(
            "Arquitectura de Software",
            "No especificado",
            "Ingeniería",
            "Conceptos de diseño, organización y mantenimiento de sistemas."
        )
        "4" -> InformacionLibro(
            "Jetpack Compose",
            "No especificado",
            "Android",
            "Desarrollo de interfaces declarativas modernas para Android."
        )
        else -> null
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(
            onClick = onVolver,
            modifier = Modifier.align(Alignment.Start)
        ) {
            Text("← Volver a Libros")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "📖",
            fontSize = 90.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        if (libro == null) {
            Text("Libro no encontrado")
        } else {
            Text(
                text = libro.nombre,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text("Nombre: ${libro.nombre}")
                    Text("Autor: ${libro.autor}")
                    Text("Categoría: ${libro.categoria}")

                    HorizontalDivider()

                    Text(
                        text = "Descripción",
                        fontWeight = FontWeight.Bold
                    )

                    Text(libro.descripcion)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onReservar,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Reservar Libro")
            }
        }
    }
}
