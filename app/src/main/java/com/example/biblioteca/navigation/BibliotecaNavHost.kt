
package com.example.biblioteca.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biblioteca.screens.DetalleReservaScreen
import com.example.biblioteca.data.ReservasStore
import com.example.biblioteca.screens.InicioScreen
import com.example.biblioteca.screens.LibrosScreen
import com.example.biblioteca.screens.DetalleLibroScreen
import com.example.biblioteca.screens.ConfirmacionScreen
import com.example.biblioteca.screens.MisReservasScreen
import com.example.biblioteca.screens.RenovarReservaScreen
import com.example.biblioteca.screens.RenovacionExitosaScreen


@Composable
fun BibliotecaNavHost() {

    val navController = rememberNavController()

    val context = LocalContext.current.applicationContext

    val reservasStore = remember(context) {
        ReservasStore(context)
    }

    NavHost(
        navController = navController,
        startDestination = Rutas.INICIO
    ) {

        // 1. INICIO
        composable(Rutas.INICIO) {
            InicioScreen(
                onVerLibros = {
                    navController.navigate(Rutas.LIBROS)
                },
                onMisReservas = {
                    navController.navigate(Rutas.MIS_RESERVAS)
                }
            )
        }

        // 2. CATALOGO
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
                    val creada = reservasStore.reservar(libroId)

                    if (creada) {
                        navController.navigate(
                            Rutas.confirmacion(libroId)
                        )
                    } else {
                        navController.navigate(
                            Rutas.MIS_RESERVAS
                        )
                    }
                }
            )
        }

        // 4. CONFIRMACION
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

        // 5. MIS RESERVAS


            composable(Rutas.MIS_RESERVAS) {
                MisReservasScreen(
                    reservas = reservasStore.reservas,
                    onVolver = {
                        navController.popBackStack()
                    },
                    onVerReserva = { reservaId ->
                        navController.navigate(
                            Rutas.detalleReserva(reservaId)
                        )
                    }
                )
            }


        composable(
            route = Rutas.DETALLE_RESERVA,
            arguments = listOf(
                navArgument("reservaId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val reservaId = backStackEntry.arguments
                ?.getString("reservaId")
                .orEmpty()

            val reserva = reservasStore.reservas.firstOrNull {
                it.id == reservaId
            }


            DetalleReservaScreen(
                reserva = reserva,

                onVolver = {
                    navController.popBackStack()
                },

                onRenovar = {
                    if (reserva != null) {
                        navController.navigate(
                            Rutas.renovarReserva(reserva.id)
                        )
                    }
                },

                onCancelar = {
                    if (reserva != null) {
                        val cancelada = reservasStore.cancelar(
                            reserva.id
                        )

                        if (cancelada) {
                            navController.popBackStack()
                        }
                    }
                }

            )

        }

// 7. RENOVAR RESERVA
        composable(
            route = Rutas.RENOVAR_RESERVA,
            arguments = listOf(
                navArgument("reservaId") {
                    type = NavType.StringType
                }
            )
        ) { entrada ->

            val id = entrada.arguments
                ?.getString("reservaId")
                .orEmpty()

            val reserva = reservasStore.reservas.firstOrNull {
                it.id == id
            }

            RenovarReservaScreen(
                reserva = reserva,
                onVolver = {
                    navController.popBackStack()
                },
                onConfirmar = { fechaNueva ->
                    val actualizada = reservasStore.renovarConFecha(
                        id,
                        fechaNueva
                    )

                    if (actualizada) {
                        navController.navigate(
                            Rutas.renovacionExitosa(id)
                        )
                    }
                }
            )
        }

// 8. RENOVACION EXITOSA
        composable(
            route = Rutas.RENOVACION_EXITOSA,
            arguments = listOf(
                navArgument("reservaId") {
                    type = NavType.StringType
                }
            )
        ) { entrada ->

            val id = entrada.arguments
                ?.getString("reservaId")
                .orEmpty()

            val reserva = reservasStore.reservas.firstOrNull {
                it.id == id
            }

            RenovacionExitosaScreen(
                reserva = reserva,
                onVolverReservas = {
                    navController.navigate(Rutas.MIS_RESERVAS) {
                        popUpTo(Rutas.MIS_RESERVAS) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }


    }

}
