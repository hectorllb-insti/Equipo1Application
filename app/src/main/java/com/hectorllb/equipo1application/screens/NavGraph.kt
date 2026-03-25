package com.hectorllb.equipo1application.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "inicio"
    ) {
        composable("inicio") {
            StartScreen(
                onRegistro = { navController.navigate("registro") },
                onLogin = { navController.navigate("login") },
                onMinijuego = { navController.navigate("minijuego") }
            )
        }

        composable("registro") {
            RegisterScreen(
                onVolver = { navController.popBackStack() },
                onRegistroExitoso = { navController.navigate("login") }
            )
        }

        composable("login") {
            LoginScreen(
                onVolver = { navController.popBackStack() },
                onLoginExitoso = { navController.navigate("history") },
            )
        }

        composable("minijuego") {
            MinijuegoScreen(
                onVolver = { navController.popBackStack() }
            )
        }

        composable("history") {
            HistoryScreen(
                onVolver = { navController.popBackStack("inicio", inclusive = false) }
            )
        }
    }
}