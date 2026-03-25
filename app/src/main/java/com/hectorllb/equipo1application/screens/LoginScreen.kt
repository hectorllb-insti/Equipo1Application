package com.hectorllb.equipo1application.screens

import androidx.compose.runtime.Composable

@Composable
fun LoginScreen(onVolver: () -> Unit = {}) {
    FormularioScreen(
        titulo = "Iniciar sesión",
        tituloTopBar = "Login",
        textBoton = "Iniciar sesión",
        onVolver = onVolver
    )
}