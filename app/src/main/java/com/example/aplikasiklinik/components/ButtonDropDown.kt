package com.example.aplikasiklinik.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.aplikasiklinik.R

@Composable
fun ButtonDropDown(
    dropDown: MutableState<Boolean>,
    buttonSize: MutableState<Size>,
    poli: MutableState<String>,
    iconDrop: Int,
    typePoli: List<String>
) {
    OutlinedButton(
        onClick = { dropDown.value = !dropDown.value },
        elevation = ButtonDefaults.elevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = MaterialTheme.colors.onBackground,
            contentColor = MaterialTheme.colors.primaryVariant
        ),
        border = BorderStroke(2.dp, MaterialTheme.colors.primaryVariant),
        modifier = Modifier
            .onGloballyPositioned { coor ->
                buttonSize.value = coor.size.toSize()
            }

    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {
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

    }

    DropdownMenu(
        modifier = Modifier
            .width(with(LocalDensity.current) {
                buttonSize.value.width.toDp()
            })
            .background(MaterialTheme.colors.background),
        expanded = dropDown.value,
        onDismissRequest = { dropDown.value = false }) {
        typePoli.forEach { item ->
            DropdownMenuItem(onClick = {
                poli.value = item
                dropDown.value = false
            }) {
                Text(
                    text = item,
                    style = MaterialTheme.typography.body2,
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.primaryVariant
                )
            }
        }
    }
}