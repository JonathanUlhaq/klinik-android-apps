package com.example.aplikasiklinik.view.main

import android.net.Uri
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aplikasiklinik.view.navigation.MainNavigation
import com.example.aplikasiklinik.view.navigation.Routes
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    dark: Boolean,
    antrian: Int,
    startDest: String = Routes.Splash.route,
    cameraClick: () -> Unit,
    uri: Uri,
    openCam:Boolean = false,
    contentCamera: @Composable () -> Unit = {},
    click: () -> Unit
) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                MainNavigation(
                    dark = dark,
                    navMain = navController,
                    antrian = antrian,
                    startDest = startDest,
                    cameraClick = { cameraClick.invoke() },
                    uri = uri,
                    contentCamera = contentCamera,
                    openCam = openCam
                ) {
                    click.invoke()
                }
            }
        }
    )
}