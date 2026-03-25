package com.hectorllb.equipo1application.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hectorllb.equipo1application.data.EtapaHistoria
import com.hectorllb.equipo1application.data.HistoryData
import com.hectorllb.equipo1application.ui.theme.AzulOscuro
import com.hectorllb.equipo1application.ui.theme.FondoGris
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    onVolver: () -> Unit = {},
) {
    // Obtenemos los datos limpios desde el archivo externo
    val etapas = HistoryData.listaEtapas
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Historia De España", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AzulOscuro
                ),
                navigationIcon = {
                    IconButton(onClick = onVolver) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver a Inicio",
                            tint = Color.White,
                        )
                    }
                }
            )
        },
        containerColor = FondoGris
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            items(etapas) { etapa ->
                TarjetaExpandible(etapa)
            }
        }
    }
}
@Composable
fun TarjetaExpandible(etapa: EtapaHistoria) {
    var expandido by remember { mutableStateOf(false) }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE1E2ED)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expandido = !expandido }
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Fila para el icono y el título
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Icono
                Image(
                    painter = painterResource(id = etapa.idImagen),
                    contentDescription = "Icono de ${etapa.titulo}",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.width(12.dp))

                // Título
                Text(
                    text = etapa.titulo,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = AzulOscuro,
                    modifier = Modifier.weight(1f) // Hace que el texto ocupe el espacio restante sin pisar el icono
                )
            }

            if (expandido) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = etapa.descripcion,
                    fontSize = 14.sp,
                    color = Color.DarkGray,
                    lineHeight = 20.sp
                )
            }
        }
    }
}