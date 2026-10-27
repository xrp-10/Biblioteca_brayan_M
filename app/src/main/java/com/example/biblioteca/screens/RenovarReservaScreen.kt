
package com.example.biblioteca.screens

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.biblioteca.data.Reserva
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun RenovarReservaScreen(
    reserva: Reserva?,
    onVolver: () -> Unit,
    onConfirmar: (LocalDate) -> Unit
) {
    val context = LocalContext.current

    val vencimiento = reserva?.fechaVencimiento?.let {
        runCatching {
            LocalDate.parse(it)
        }.getOrNull()
    } ?: LocalDate.now()

    val fechaMinima = maxOf(
        vencimiento,
        LocalDate.now()
    ).plusDays(1)

    var fechaNueva by remember(reserva?.id) {
        mutableStateOf(fechaMinima)
    }

    val formato = DateTimeFormatter.ofPattern(
        "dd/MM/yyyy"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        TextButton(
            onClick = onVolver
        ) {
            Text("← Volver")
        }

        Text(
            text = "Renovar Reserva",
            style = MaterialTheme.typography.headlineMedium
        )

        if (reserva == null) {

            Text("Reserva no encontrada.")

        } else {

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement =
                        Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = "Libro ID: ${reserva.libroId}"
                    )

                    Text(
                        text = "Vencimiento actual: " +
                                vencimiento.format(formato)
                    )

                    Text(
                        text = "Nueva fecha: " +
                                fechaNueva.format(formato)
                    )
                }
            }

            OutlinedButton(
                onClick = {
                    DatePickerDialog(
                        context,
                        { _, anio, mes, dia ->
                            fechaNueva = LocalDate.of(
                                anio,
                                mes + 1,
                                dia
                            )
                        },
                        fechaNueva.year,
                        fechaNueva.monthValue - 1,
                        fechaNueva.dayOfMonth
                    ).apply {

                        datePicker.minDate = fechaMinima
                            .atStartOfDay(
                                ZoneId.systemDefault()
                            )
                            .toInstant()
                            .toEpochMilli()

                    }.show()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Seleccionar nueva fecha")
            }

            Button(
                onClick = {
                    if (fechaNueva >= fechaMinima) {
                        onConfirmar(fechaNueva)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirmar Renovación")
            }
        }
    }
}
