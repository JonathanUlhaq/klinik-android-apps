package com.example.aplikasiklinik.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.aplikasiklinik.R

val  nunito = FontFamily(listOf(
    Font(R.font.nunito_regular,
        weight = FontWeight.Normal),
    Font(R.font.nunito_light,
        weight = FontWeight.Light),
    Font(R.font.nunito_bold,
        weight = FontWeight.Bold),
    Font(R.font.nunito_semibold,
        weight = FontWeight.SemiBold),
    Font(R.font.nunito_medium,
        weight = FontWeight.Medium),
))

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    ),
    body2 = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontFamily = nunito,
        fontWeight = FontWeight.Light,
        fontSize = 12.sp
    ),

)