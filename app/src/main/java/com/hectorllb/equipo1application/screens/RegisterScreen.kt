package com.hectorllb.equipo1application.screens

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.hectorllb.equipo1application.controllers.DatabaseController
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme

//
@Composable
fun RegisterScreen(

    onVolver: () -> Unit = {},
    onRegistroExitoso: () -> Unit = {}
) {
    val context = LocalContext.current
    val dbHelper = DatabaseController(context)

    // Variables de estado para controlar el AlertDialog de error
    var mostrarDialogoError by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    FormularioScreen(
        titulo = "Crear cuenta",
        tituloTopBar = "Registro",
        textBoton = "Aceptar",
        onVolver = onVolver,
        onAccion = { usuarioInput, passwordInput ->
            // Comprobación 1: Campos vacíos
            if (usuarioInput.isEmpty() || passwordInput.isEmpty()) {
                mensajeError = "Por favor, rellena todos los campos."
                mostrarDialogoError = true
            }
            // Comprobación 2: Validar contraseña (1 mayúscula y 1 carácter especial)
            else if (!passwordInput.any { it.isUpperCase() } || !passwordInput.any { !it.isLetterOrDigit() }) {
                mensajeError = "La contraseña debe contener una mayúscula y un carácter especial."
                mostrarDialogoError = true
            }
            // Si el usuario existe en nuestra base de datos, le dejamos pasar
            else {
                val exito = dbHelper.registerUser(usuarioInput, passwordInput)
                if (exito) {
                    // Mantenemos el Toast para el éxito (opcional) y navegamos
                    Toast.makeText(context, "Registro exitoso", Toast.LENGTH_SHORT).show()
                    onRegistroExitoso() // Navegamos directamente al Login
                } else {
                    mensajeError = "Error: El nombre de usuario ya existe."
                    mostrarDialogoError = true
                }
            }
        }
    )

    if (mostrarDialogoError) {
        AlertDialog(
            onDismissRequest = { mostrarDialogoError = false },
            title = { Text("¡Cuidado!") },
            text = { Text(mensajeError) },
            confirmButton = {
                TextButton(onClick = { mostrarDialogoError = false }) {
                    Text("Aceptar")
                }
            }
        )
    }

}

@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewRegister() {
    Equipo1ApplicationTheme {
        RegisterScreen()
    }
}