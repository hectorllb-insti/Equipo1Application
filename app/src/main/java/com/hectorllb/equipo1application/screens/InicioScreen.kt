package com.hectorllb.equipo1application.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hectorllb.equipo1application.ui.theme.AzulOscuro
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme
import com.hectorllb.equipo1application.ui.theme.FondoGris
import com.hectorllb.equipo1application.ui.theme.MoradoClaro

// ── Pantalla principal ────────────────────────────────────────
@Composable
fun InicioScreen() {
    Scaffold(
        topBar = { InicioTopBar() },
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
                // Título
                Text(
                    text = "Bienvenido",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Botón Login (relleno)
                BotonPrincipal(
                    texto = "Login",
                    onClick = { /* TODO: navegar a Login */ }
                )

                // Botón Registrarse (contorno)
                BotonContorno(
                    texto = "Registrarse",
                    onClick = { /* TODO: navegar a Registro */ }
                )

                // Botón Miuguago (color morado)
                BotonSecundario(
                    texto = "Miuguago",
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

//Top Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InicioTopBar() {
    TopAppBar(
        title = { Text("Inicio", color = Color.White) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AzulOscuro
        )
    )
}

//Botones reutilizables
@Composable
fun BotonPrincipal(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = AzulOscuro)
    ) {
        Text(texto, color = Color.White, fontSize = 16.sp)
    }
}

@Composable
fun BotonContorno(texto: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = AzulOscuro)
    ) {
        Text(texto, fontSize = 16.sp)
    }
}

@Composable
fun BotonSecundario(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MoradoClaro)
    ) {
        Text(texto, color = Color.White, fontSize = 16.sp)
    }
}

//Preview
@Preview(showSystemUi = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewInicio() {
    Equipo1ApplicationTheme {
        InicioScreen()
    }
}
