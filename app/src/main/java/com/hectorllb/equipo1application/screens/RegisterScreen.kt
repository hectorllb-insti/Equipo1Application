package com.hectorllb.equipo1application.screens

import android.content.res.Configuration
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme

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

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegister() {
    Equipo1ApplicationTheme {
        RegisterScreen()
    }
}