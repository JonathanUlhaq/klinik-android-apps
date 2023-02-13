package com.example.aplikasiklinik.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = DarkPrimary,
    secondary = DarkSecondary,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    error = DarkError,
    primaryVariant = DarkPrimaryVariant,
    secondaryVariant = DarkSecondaryVariant,
    onPrimary = DarkPrimarySurface,
    onSecondary = DarkOnSecondary
)

private val LightColorPalette = lightColors(
    primary = LightPrimary,
    secondary = LightSecondary,
    surface = LightSurface,
    onSurface = LightOnSurface,
    background = LightBackground,
    onBackground = LightOnBackground,
    error = LightError,
    primaryVariant = LightPrimaryVariant,
    secondaryVariant = LightSecondaryVariant,
    onPrimary = LightPrimarySurface ,
    onSecondary = LightOnSecondary
)

@Composable
fun AplikasiKlinikTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    if(darkTheme){
        systemUiController.setSystemBarsColor(
            color = MaterialTheme.colors.onPrimary
        )
    }else{
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.primaryVariant
        )

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colors.background
        )
    }
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}