package com.hectorllb.equipo1application.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hectorllb.equipo1application.controllers.DatabaseController
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme

@Composable
fun LoginScreen(
    onVolver: () -> Unit = {},
    onLoginExitoso: () -> Unit = {}
) {
    val context = LocalContext.current
    val dbHelper = DatabaseController(context)

    FormularioScreen(
        titulo = "Iniciar sesión",
        tituloTopBar = "Login",
        textBoton = "Iniciar sesión",
        onVolver = onVolver,
        onAccion = { usuarioInput, passwordInput ->
            if (usuarioInput.isNotEmpty() && passwordInput.isNotEmpty()) {
                // Comprobamos si nuestro usuario existe
                val existe = dbHelper.checkUser(usuarioInput, passwordInput)
                if (existe) {
                    Toast.makeText(context, "¡Bienvenido!", Toast.LENGTH_SHORT).show()
                    onLoginExitoso() // Navegamos al historial
                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
            }
        }
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