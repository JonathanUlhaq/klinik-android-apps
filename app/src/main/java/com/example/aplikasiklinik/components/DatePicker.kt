package com.example.aplikasiklinik.components

import android.app.DatePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R
import java.util.*

@Composable
fun DatePicker(
    context:Context,
    date:MutableState<String>,
    color: Color = Color.White,
    colorSecond: Color = Color.White,
    boolean: Boolean = true
) {
    val year:Int
    val month:Int
    val day:Int

    val calendar = Calendar.getInstance()

    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)

    calendar.time = Date()

    val datePickerDialog = DatePickerDialog(
        context,
            { _: DatePicker, year: Int, month: Int, day: Int ->
                date.value = "$day/${month+1}/$year"
            }, year, month, day
    )
    
    Surface(
        color = Color.Transparent,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { if (boolean) datePickerDialog.show() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp,color)
    ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                    Icon(painter = painterResource(id = R.drawable.date_icon_svg),
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier
                            .size(30.dp))

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = date.value.ifEmpty { stringResource(id = R.string.tanggal_lahir) },
                    style = MaterialTheme.typography.h1,
                    fontSize = 12.sp,
                    color = colorSecond
                )
            }
    }

}