package com.hectorllb.equipo1application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.hectorllb.equipo1application.screens.NavGraph
import com.hectorllb.equipo1application.ui.theme.Equipo1ApplicationTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Equipo1ApplicationTheme {
                NavGraph()
            }
        }
    }
}



