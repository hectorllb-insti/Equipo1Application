package com.hectorllb.equipo1application.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme

@Composable
fun LoginScreen(onVolver: () -> Unit = {}) {
    FormularioScreen(
        titulo = "Iniciar sesión",
        tituloTopBar = "Login",
        textBoton = "Iniciar sesión",
        onVolver = onVolver
    )
}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewLogin() {
    Equipo1ApplicationTheme {
        LoginScreen()
    }
}