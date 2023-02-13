package com.example.aplikasiklinik.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DisableInput() {
    OutlinedTextField(value = "",
        onValueChange = {  },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Color.White,
            backgroundColor = MaterialTheme.colors.primary,
            disabledBorderColor = MaterialTheme.colors.onSurface.copy(0.8F),
            disabledLabelColor = MaterialTheme.colors.onSurface.copy(0.8F),
            trailingIconColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White
        ),
        label = {
            Text(
                text = "+62",
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp
            )
        },
        shape = RoundedCornerShape(12.dp),
        enabled = false,
        modifier = Modifier
            .width(60.dp)
    )
}