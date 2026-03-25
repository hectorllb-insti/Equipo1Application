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
                onLogin = { navController.navigate("login") }
            )
        }
        composable("registro") {
            RegisterScreen(
                onVolver = { navController.popBackStack() }
            )
        }
        composable("login") {
            LoginScreen(
                onVolver = { navController.popBackStack() }
            )
        }
    }
}