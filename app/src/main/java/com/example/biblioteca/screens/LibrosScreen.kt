
package com.example.biblioteca.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

data class Libro(
    val id: String,
    val nombre: String,
    val categoria: String
)

@Composable
fun LibrosScreen(
    onVerDetalle: (String) -> Unit
) {
    val libros = listOf(
        Libro("1", "Clean Code", "Programación"),
        Libro("2", "Kotlin para Android", "Android"),
        Libro("3", "Arquitectura de Software", "Ingeniería"),
        Libro("4", "Jetpack Compose", "Android")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "📚 Mis Libros",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Elige un libro para ver más detalles.")

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(libros, key = { it.id }) { libro ->
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = libro.nombre,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = libro.categoria,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Button(
                            onClick = {
                                onVerDetalle(libro.id)
                            }
                        ) {
                            Text("Ver Detalle")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LibrosScreenPreview() {
    LibrosScreen(onVerDetalle = {})
}
