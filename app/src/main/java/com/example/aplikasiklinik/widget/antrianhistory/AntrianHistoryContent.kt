package com.example.aplikasiklinik.widget.antrianhistory

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.aplikasiklinik.widget.jadwal.FontContent

@Composable
fun AntrianHistoryContent(
    dropDown: MutableState<Boolean>,
    iconDrop: Int
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.onBackground,
        modifier = Modifier
            .padding(start = 14.dp, end = 14.dp, top = 8.dp)
            .fillMaxWidth()
            .clickable { dropDown.value = !dropDown.value },
        contentColor = MaterialTheme.colors.primaryVariant
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "01/01/2023",
                    style = MaterialTheme.typography.h2
                )
                IconButton(onClick = { dropDown.value = !dropDown.value }) {
                    Icon(
                        painter = painterResource(id = iconDrop),
                        contentDescription = null,
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
            }
            AnimatedVisibility(visible = dropDown.value) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    FontContent(
                        title = "Poli : ",
                        desc = "Umum ",
                        size = 16,
                        color = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    FontContent(
                        title = "Status : ",
                        desc = "Sudah dilayani",
                        size = 16,
                        color = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}