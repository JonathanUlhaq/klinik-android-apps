package com.example.aplikasiklinik.view.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.aplikasiklinik.components.BotNavBar
import com.example.aplikasiklinik.view.navigation.NavigationAdapter
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeMenu(dark:Boolean,
             click:()->Unit) {
    val navController = rememberAnimatedNavController()
    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .padding(it)
            ) {
                NavigationAdapter(dark = dark, navController= navController ) {
                    click.invoke()
                }
            }
        },
        bottomBar = {
            BotNavBar(navController = navController)
        }
    )
}