package com.example.aplikasiklinik.view.mainfitur

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.aplikasiklinik.view.navigation.ContentNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainFitur(
    route:String,
    dark:Boolean,
    click:() -> Unit
) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                ContentNavigation(route = route, navController = navController,dark = dark,click = {
                    click.invoke()
                })
            }
        }
    )
}