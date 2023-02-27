package com.example.aplikasiklinik.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R

@Composable
fun EditButton(boolean: Boolean,click: () -> Unit) {

    val icons by animateIntAsState(targetValue = if (!boolean) R.drawable.save_icon else R.drawable.edit_icon)
    val colors by animateColorAsState(targetValue = if (!boolean) MaterialTheme.colors.primary else MaterialTheme.colors.secondary)

    Button(
        onClick = { click.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors,
            contentColor = MaterialTheme.colors.onSurface
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            painter = painterResource(id = icons),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = if (boolean) "Simpan" else "Ubah",
            style = MaterialTheme.typography.body2
        )
    }
}