package com.hectorllb.equipo1application.screens

import androidx.compose.runtime.*

//
@Composable
fun RegisterScreen(onVolver: () -> Unit = {}) {
    FormularioScreen(
        titulo = "Crear cuenta",
        tituloTopBar = "Registro",
        textBoton = "Aceptar",
        onVolver = onVolver
    )
}