package com.example.aplikasiklinik.view.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aplikasiklinik.view.navigation.MainNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(dark:Boolean,
             click:()->Unit) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                MainNavigation(dark = dark, navMain = navController ) {
                    click.invoke()
                }
            }
        }
    )
}