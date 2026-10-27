
package com.example.biblioteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.biblioteca.screens.InicioScreen
import com.example.biblioteca.screens.LibrosScreen
import com.example.biblioteca.screens.DetalleLibroScreen
import com.example.biblioteca.screens.ConfirmacionScreen

@Composable
fun BibliotecaNavHost() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {

        // 1. PANTALLA DE INICIO
        composable(Rutas.INICIO) {
            InicioScreen(
                onVerLibros = {
                    navController.navigate(Rutas.LIBROS)
                }
            )
        }

        // 2. LISTA DE LIBROS
        composable(Rutas.LIBROS) {
            LibrosScreen(
                onVerDetalle = { libroId ->
                    navController.navigate(
                        Rutas.detalleLibro(libroId)
                    )
                }
            )
        }

        // 3. DETALLE DEL LIBRO
        composable(
            route = Rutas.DETALLE,
            arguments = listOf(
                navArgument("libroId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val libroId = backStackEntry.arguments
                ?.getString("libroId")
                .orEmpty()

            DetalleLibroScreen(
                libroId = libroId,
                onVolver = {
                    navController.popBackStack()
                },
                onReservar = {
                    navController.navigate(
                        Rutas.confirmacion(libroId)
                    )
                }
            )
        }

        // 4. CONFIRMACION DE RESERVA
        // AQUÍ VA EL NUEVO BLOQUE
        composable(
            route = Rutas.CONFIRMACION,
            arguments = listOf(
                navArgument("libroId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val libroId = backStackEntry.arguments
                ?.getString("libroId")
                .orEmpty()

            ConfirmacionScreen(
                libroId = libroId,
                onVolverInicio = {
                    navController.navigate(Rutas.INICIO) {
                        popUpTo(Rutas.INICIO) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

    } // Cierra NavHost

} // Cierra BibliotecaNavHost
