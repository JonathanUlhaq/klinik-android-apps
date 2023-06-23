package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

@Composable
fun AntrianTitleHeader(
    dark: Boolean,
    name:String,
    click: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top,
    ) {
        Column {
            Text(
                text = stringResource(R.string.selamat_datang),
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                fontSize = 20.sp
            )
        }





        Spacer(modifier = Modifier.weight(1F))
        IconButton(onClick = {
            click.invoke()
        }) {
            Icon(
                painter = painterResource(id = if (!dark) R.drawable.darkmode_icon else R.drawable.sun_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp),
                tint = MaterialTheme.colors.onSurface
            )
        }
    }
}