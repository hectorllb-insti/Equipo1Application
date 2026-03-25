package com.hectorllb.equipo1application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hectorllb.equipo1application.ui.theme.AzulOscuro
import com.hectorllb.equipo1application.ui.theme.FondoGris

@Composable
fun FormularioScreen (
    titulo: String,          // "Crear cuenta" o "Iniciar sesión"
    tituloTopBar: String,    // "Registro" o "Login"
    textBoton: String,       // "Aceptar" o "Iniciar sesión"
    onVolver: () -> Unit = {},
    onAccion: (String, String) -> Unit
) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            FormularioTopBar(
                titulo = tituloTopBar,
                onVolver = onVolver
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 40.dp)
            ) {
                Text(
                    text = titulo,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    placeholder = { Text("Nombre de usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                OutlinedTextField(
                    value = contrasena,
                    onValueChange = { contrasena = it },
                    placeholder = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation()
                )

                BotonPrincipal(
                    texto = textBoton,
                    onClick = {
                        onAccion(usuario, contrasena)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioTopBar(titulo: String, onVolver: () -> Unit) {
    TopAppBar(
        title = { Text(titulo, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = onVolver) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Volver",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = AzulOscuro)
    )
}