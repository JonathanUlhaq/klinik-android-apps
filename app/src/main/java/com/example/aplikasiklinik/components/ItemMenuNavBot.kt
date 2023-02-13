package com.example.aplikasiklinik.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

@Composable
fun ItemMenuNavBot(
    index:Int
) {

    val title = listOf(
        stringResource(id = R.string.antrian),
        stringResource(id = R.string.jam),
        stringResource(id = R.string.profile),
    )

    val icon = listOf(
        R.drawable.queue_menu_icon_,
        R.drawable.schedule_menu_icon,
        R.drawable.profile_menu_icon
    )
    Row(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp, start = 12.dp, end = 12.dp)
    ) {
        title.forEachIndexed { indexs, item ->
            val color by animateColorAsState(targetValue = if (index == indexs) MaterialTheme.colors.onSurface else MaterialTheme.colors.onSurface.copy(0.4F)  )

            IconButton(onClick = { /*TODO*/ }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = icon[indexs]),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp),
                        tint = color
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = item,
                        style = MaterialTheme.typography.h1,
                        color = color,
                        fontSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
    }





}