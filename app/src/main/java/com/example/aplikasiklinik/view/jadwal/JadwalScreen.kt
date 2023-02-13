package com.example.aplikasiklinik.view.jadwal

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.aplikasiklinik.components.BotNavBar

@Composable
fun JadwalScreen(
    navController:NavController
) {
    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            Text(text = "Jadwal",
                color = Color.Black)
        }
    }
}