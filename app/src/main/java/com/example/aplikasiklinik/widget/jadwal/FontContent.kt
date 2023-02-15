package com.example.aplikasiklinik.widget.jadwal

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

@Composable
fun FontContent(
    title:String,
    desc:String,
    size:Int = 12,
    color:Color = MaterialTheme.colors.surface
) {
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = color,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                fontSize = size.sp
            )
        ) {
            append(title)
        }

        withStyle(
            style = SpanStyle(
                color = color,
                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                fontSize = size.sp
            )
        ) {
            append(desc)
        }


    }
    )
}