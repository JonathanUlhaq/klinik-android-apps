package com.example.aplikasiklinik.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

@Composable
fun ButtonDropDown(
    dropDown: MutableState<Boolean>,
    poli: MutableState<String>,
    iconDrop: Int,
    clickValue:() -> Unit
) {

    val listPoli = listOf(
        stringResource(R.string.poli_umum),
        stringResource(R.string.poli_gigi)
    )

    Surface(
        elevation = 0.dp,
        shape = RoundedCornerShape(20.dp),
        color = MaterialTheme.colors.onBackground,
        contentColor = MaterialTheme.colors.primaryVariant,
        border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .clickable { dropDown.value = !dropDown.value }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.poli_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = poli.value,
                        style = MaterialTheme.typography.h1,
                        fontSize = 12.sp,
                    )
                }
                Spacer(modifier = Modifier.weight(1F))
                Icon(
                    painter = painterResource(id = iconDrop),
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp)
                )
            }
            Spacer(modifier = Modifier.height(14.dp))

            AnimatedVisibility(visible = dropDown.value) {
                Column {
                    Divider(
                        color = MaterialTheme.colors.surface.copy(0.18F),
                        modifier = Modifier
                            .clip(RoundedCornerShape(100))
                    )
                    listPoli.forEach {
                        Spacer(modifier = Modifier.height(14.dp))
                        Surface(
                            color = Color.Transparent,
                            contentColor = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    poli.value = it
                                    dropDown.value = false
                                    clickValue.invoke()
                                }
                        ) {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.h1,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                        Divider(
                            color = MaterialTheme.colors.surface.copy(0.18F),
                            modifier = Modifier
                                .clip(RoundedCornerShape(100))
                        )
                    }
                }
            }
        }
    }


//    DropdownMenu(
//        modifier = Modifier
//            .width(with(LocalDensity.current) {
//                buttonSize.value.width.toDp()
//            })
//            .background(MaterialTheme.colors.background),
//        expanded = dropDown.value,
//        onDismissRequest = { dropDown.value = false }) {
//        typePoli.forEach { item ->
//            DropdownMenuItem(onClick = {
//                poli.value = item
//                dropDown.value = false
//            }) {
//                Text(
//                    text = item,
//                    style = MaterialTheme.typography.body2,
//                    fontSize = 12.sp,
//                    color = MaterialTheme.colors.primaryVariant
//                )
//            }
//        }
//    }
}