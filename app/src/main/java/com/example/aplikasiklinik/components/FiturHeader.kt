package com.example.aplikasiklinik.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun FiturHeader() {
    Box(
        modifier = Modifier
            .height(90.dp)
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                .height(40.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colors.onPrimary)
        )
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = MaterialTheme.colors.onPrimary,
                border = BorderStroke(7.dp, MaterialTheme.colors.background),
                shape = CircleShape,
                elevation = 0.dp
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(MaterialTheme.colors.onPrimary)
                )
            }
        }
    }
}