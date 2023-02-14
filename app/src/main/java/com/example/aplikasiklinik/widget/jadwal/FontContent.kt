package com.example.aplikasiklinik.widget.jadwal

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
    desc:String
) {
    Text(text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.surface,
                fontFamily = FontFamily(Font(R.font.nunito_bold)),
                fontSize = 12.sp
            )
        ) {
            append(title)
        }

        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colors.surface,
                fontFamily = FontFamily(Font(R.font.nunito_medium)),
                fontSize = 12.sp
            )
        ) {
            append(desc)
        }


    }
    )
}