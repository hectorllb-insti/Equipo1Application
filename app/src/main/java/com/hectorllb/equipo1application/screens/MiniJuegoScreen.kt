package com.hectorllb.equipo1application.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hectorllb.equipo1application.ui.theme.AzulOscuro
import com.hectorllb.equipo1application.ui.theme.FondoGris
import kotlinx.coroutines.delay
import kotlin.random.Random

// Colores base de los 4 botones del Simon
private val colorRojo = Color(0xFFD32F2F)
private val colorVerde = Color(0xFF388E3C)
private val colorAzul = Color(0xFF1976D2)
private val colorAmarillo = Color(0xFFFBC02D)

// Colores iluminados (mas claros)
private val colorRojoLit = Color(0xFFFF8A80)
private val colorVerdeLit = Color(0xFFA5D6A7)
private val colorAzulLit = Color(0xFF90CAF9)
private val colorAmarilloLit = Color(0xFFFFF59D)

// Lista de los 4 colores en orden de indice
private val coloresBase = listOf(colorRojo, colorVerde, colorAzul, colorAmarillo)
private val coloresLit = listOf(colorRojoLit, colorVerdeLit, colorAzulLit, colorAmarilloLit)

// ── Pantalla del Minijuego: Simon Dice ────────────────────────────
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MinijuegoScreen(
    onVolver: () -> Unit = {}
) {
    val rondasParaGanar = 10
    // Secuencia generada por el juego (indices 0-3)
    var secuencia by remember {
        mutableStateOf(listOf<Int>())
    }

    // Indice actual de la secuencia que el jugador debe pulsar
    var indiceJugador by remember {
        mutableStateOf(0)
    }

    // Ronda actual (empieza en 1)
    var ronda by remember {
        mutableStateOf(0)

    }
    // Estado del juego
    var estadoJuego by remember {
        mutableStateOf("esperando") } // esperando, mostrando, jugando, fallado

    // Indice del boton iluminado durante la secuencia (-1 = ninguno)
    var botonIluminado by remember {
        mutableStateOf(-1)
    }

    // Mejor puntuacion de la sesion
    var mejorPuntuacion by remember {
        mutableStateOf(0)
    }
    // Bloquear input mientras se muestra la secuencia
    var inputBloqueado by remember {
        mutableStateOf(true)
    }

    // Boton iluminado por el jugador al pulsar
    var botonPulsado by remember {
        mutableStateOf(-1)
    }

    // Funcion para iniciar una nueva ronda
    fun nuevaRonda() {
        secuencia = secuencia + Random.nextInt(4)
        ronda = secuencia.size
        estadoJuego = "mostrando"
        indiceJugador = 0
        inputBloqueado = true
    }

    // Funcion para reiniciar el juego
    fun reiniciarJuego() {
        secuencia = listOf()
        ronda = 0
        indiceJugador = 0
        estadoJuego = "esperando"
        botonIluminado = -1
        inputBloqueado = true
        botonPulsado = -1
    }

    // Efecto para mostrar la secuencia al jugador
    LaunchedEffect(estadoJuego, ronda) {
        if (estadoJuego == "mostrando") {
            delay(600L) // Pausa antes de empezar a mostrar
            for (i in secuencia.indices) {
                botonIluminado = secuencia[i]
                delay(600L)
                botonIluminado = -1
                delay(300L)
            }
            estadoJuego = "jugando"
            inputBloqueado = false
        }
    }

    // Efecto para apagar el boton pulsado por el jugador
    LaunchedEffect(botonPulsado) {
        if (botonPulsado >= 0) {
            delay(250L)
            botonPulsado = -1
        }
    }

    // Cuando el jugador pulsa un boton
    fun onBotonPulsado(indice: Int) {
        if (inputBloqueado || estadoJuego != "jugando") return

        botonPulsado = indice

        if (indice == secuencia[indiceJugador]) {
            // Acierto
            indiceJugador++
            if (indiceJugador >= secuencia.size) {
                inputBloqueado = true
                if (ronda > mejorPuntuacion) {
                    mejorPuntuacion = ronda
                }
                if (ronda >= rondasParaGanar) {
                    estadoJuego = "ganado"
                } else {
                    nuevaRonda()
                }
            }
        } else {
            // Fallo
            if (ronda > mejorPuntuacion) {
                mejorPuntuacion = ronda - 1
            }
            estadoJuego = "fallado"
            inputBloqueado = true
        }
    }

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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Titulo y puntuacion
            Text(
                text = "Simon Dice",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = AzulOscuro
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = if (ronda > 0) "Ronda: $ronda" else "Pulsa Empezar",
                fontSize = 18.sp,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Mejor racha: $mejorPuntuacion",
                fontSize = 14.sp,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Grid 2x2 de botones de colores
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth(0.75f)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BotonSimon(
                        indice = 0,
                        iluminado = botonIluminado == 0 || botonPulsado == 0,
                        modifier = Modifier.weight(1f),
                        onClick = { onBotonPulsado(0) }
                    )
                    BotonSimon(
                        indice = 1,
                        iluminado = botonIluminado == 1 || botonPulsado == 1,
                        modifier = Modifier.weight(1f),
                        onClick = { onBotonPulsado(1) }
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    BotonSimon(
                        indice = 2,
                        iluminado = botonIluminado == 2 || botonPulsado == 2,
                        modifier = Modifier.weight(1f),
                        onClick = { onBotonPulsado(2) }
                    )
                    BotonSimon(
                        indice = 3,
                        iluminado = botonIluminado == 3 || botonPulsado == 3,
                        modifier = Modifier.weight(1f),
                        onClick = { onBotonPulsado(3) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Boton de empezar / reiniciar
            if (estadoJuego == "esperando") {
                BotonPrincipal(
                    texto = "Empezar",
                    onClick = { nuevaRonda() }
                )
            }

            // Texto de estado
            if (estadoJuego == "mostrando") {
                Text(
                    text = "Observa la secuencia...",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            } else if (estadoJuego == "jugando") {
                Text(
                    text = "Tu turno - repite la secuencia",
                    fontSize = 16.sp,
                    color = AzulOscuro,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Dialogo de fallo
        if (estadoJuego == "fallado") {
            AlertDialog(
                onDismissRequest = { },
                title = {
                    Text(
                        text = "Has fallado",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "Has llegado a la ronda ${ronda - 1}.\nMejor racha: $mejorPuntuacion",
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        reiniciarJuego()
                    }) {
                        Text("Jugar de nuevo", color = AzulOscuro)
                    }
                },
                dismissButton = {
                    TextButton(onClick = onVolver) {
                        Text("Volver al inicio", color = Color.Gray)
                    }
                }
            )
        }

        // Dialogo de victoria
        if (estadoJuego == "ganado") {
            AlertDialog(
                onDismissRequest = { },
                title = {
                    Text(
                        text = "Objetivo alcanzado",
                        fontWeight = FontWeight.Bold
                    )
                },
                text = {
                    Text(
                        text = "Has completado las $rondasParaGanar rondas.",
                        textAlign = TextAlign.Center
                    )
                },
                confirmButton = {
                    TextButton(onClick = onVolver) {
                        Text("Volver al inicio", color = AzulOscuro)
                    }
                }
            )
        }
    }
}

// Boton de color individual
@Composable
fun BotonSimon(
    indice: Int,
    iluminado: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorActual by animateColorAsState(
        targetValue = if (iluminado) coloresLit[indice] else coloresBase[indice],
        animationSpec = tween(durationMillis = 150),
        label = "colorBoton"
    )

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(colorActual)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        // Vacio - solo el color
    }
}
