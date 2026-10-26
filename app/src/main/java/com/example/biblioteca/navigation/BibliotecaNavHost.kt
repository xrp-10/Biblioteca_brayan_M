
package com.example.biblioteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biblioteca.screens.InicioScreen
import com.example.biblioteca.screens.LibrosScreen

@Composable
fun BibliotecaNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {

        composable(Rutas.INICIO) {
            InicioScreen(
                onVerLibros = {
                    navController.navigate(Rutas.LIBROS) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Rutas.LIBROS) {
            LibrosScreen(
                onVerDetalle = { libroId ->
                    // Próxima etapa:
                    // navegar al detalle del libro seleccionado.
                }
            )
        }
    }
}
