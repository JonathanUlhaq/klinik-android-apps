package com.example.aplikasiklinik

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PagerDot(
    index:Int,
    currentIndex:Int,
    color:Color = MaterialTheme.colors.primary.copy(0.6F)
) {

    val indexed = remember {
        mutableStateOf(false)
    }

    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
            .padding(top=5.dp)
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.Center,
            content = {
                items(index) {

                    indexed.value =  it == currentIndex
                    val sizeWidth by animateIntAsState(targetValue = if (indexed.value) 20 else 8)

                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clip(CircleShape)
                            .size(height = 8.dp, width = sizeWidth.dp)
                            .background(color)
                    )
                }
            })
    }
}