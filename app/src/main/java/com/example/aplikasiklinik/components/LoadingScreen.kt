package com.example.aplikasiklinik.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.window.Dialog

@Composable
fun LoadingScreen(boolean:Boolean) {
    if (boolean) {
        Dialog(onDismissRequest = {  }) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.onPrimary
            )
        }
    }
}