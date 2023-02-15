package com.example.aplikasiklinik.view.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.aplikasiklinik.view.antrianhistory.AntrianHistory
import com.example.aplikasiklinik.view.currentantrian.CurrentAntrian
import com.example.aplikasiklinik.view.home.HomeMenu
import com.example.aplikasiklinik.view.registantrian.RegistAntrian
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentNavigation(
    route: String,
    dark: Boolean,
    click: () -> Unit,
    navController: NavHostController
) {

    AnimatedNavHost(navController = navController, startDestination = route) {

        composable(Routes.CurrentAntrian.route,
            enterTransition = {
                fadeIn(tween(700))
            }) {
            CurrentAntrian(navController)
        }

        composable(Routes.AntrianHistory.route,
            enterTransition = {
                fadeIn(tween(700))
            }) {
            AntrianHistory(navController)
        }

        composable(Routes.AntrianRegister.route,
            enterTransition = {
                fadeIn(tween(700))
            }) {
            RegistAntrian(navController)
        }

        composable(Routes.Home.route,
            enterTransition = {
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(300))
            },
            exitTransition = {
                fadeOut(tween(700))
            }) {
            HomeMenu(dark = dark) {
                click.invoke()
            }
        }

    }
}