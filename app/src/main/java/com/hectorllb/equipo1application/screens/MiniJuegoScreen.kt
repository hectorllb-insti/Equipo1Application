package com.hectorllb.equipo1application.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.hectorllb.equipo1application.ui.theme.AzulOscuro
import com.hectorllb.equipo1application.ui.theme.FondoGris

// ── Pantalla del Minijuego ────────────────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinijuegoScreen(
    onVolver: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minijuego", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulOscuro
                ),
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = FondoGris
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // Desarrollo de la lógica del minijuego más adelante
            Text(
                text = "Pantalla del Minijuego en construcción",
                fontSize = 20.sp,
                color = Color.DarkGray
            )
        }
    }
}
