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

@Composable
fun DisableInputOutlined() {
    OutlinedTextField(value = "",
        onValueChange = {  },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.surface,
            backgroundColor = Color.Transparent,
            disabledBorderColor = MaterialTheme.colors.primaryVariant.copy(0.7F),
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            leadingIconColor = MaterialTheme.colors.primaryVariant,
            disabledLeadingIconColor = MaterialTheme.colors.primaryVariant.copy(0.7F),
            disabledTextColor = MaterialTheme.colors.surface.copy(0.7F)
        ),
        label = {
            Text(
                text = "+62",
                style = MaterialTheme.typography.h1,
                fontSize = 12.sp,
                color = MaterialTheme.colors.primary
            )
        },
        shape = RoundedCornerShape(12.dp),
        enabled = false,
        modifier = Modifier
            .width(60.dp)
    )
}