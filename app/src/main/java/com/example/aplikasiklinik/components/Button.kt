package com.example.aplikasiklinik.components

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ButtonClick(
    color:Color,
    textColor:Color = Color.White,
    text:String,
    modifier: Modifier = Modifier,
    click:()->Unit
) {
    Button(
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        modifier = modifier
            .imePadding(),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  color,
            contentColor = textColor
        ),
        onClick = { click.invoke() }) {
        Text(
            text = text,
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .padding(4.dp)
        )
    }
}