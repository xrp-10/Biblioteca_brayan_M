
package com.example.biblioteca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.biblioteca.screens.InicioScreen
import com.example.biblioteca.ui.theme.BibliotecaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BibliotecaTheme {
                InicioScreen(
                    onVerLibros = {
                        // Próximamente Navigation Compose
                    }
                )
            }
        }
    }
}
