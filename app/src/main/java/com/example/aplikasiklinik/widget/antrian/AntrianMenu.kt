package com.example.aplikasiklinik.widget.antrian

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.R

@Composable
fun AntrianMenu(
    title:String,
    desc:String,
    color: Color,
    icon:Int,
    click:() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = CircleShape,
            color = color.copy(0.14F),
            modifier = Modifier
                .clickable {
                    click.invoke()
                }
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .padding(10.dp)
                    .size(25.dp)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.surface
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.surface.copy(0.45F),
                modifier = Modifier
                    .width(190.dp)
            )
        }
        Spacer(modifier = Modifier.weight(0.5F))
        IconButton(onClick = { click.invoke() }) {
            Icon(
                painter = painterResource(R.drawable.enter_icon),
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(20.dp)
            )
        }

    }
}