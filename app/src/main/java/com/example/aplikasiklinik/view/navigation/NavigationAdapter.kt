package com.example.aplikasiklinik.view.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.aplikasiklinik.view.antrian.AntrianScreen
import com.example.aplikasiklinik.view.jadwal.JadwalScreen
import com.example.aplikasiklinik.view.mainfitur.MainFitur
import com.example.aplikasiklinik.view.profil.ProfilScreen
import com.example.aplikasiklinik.view.profil.ProfilViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter(
    dark:Boolean,
    navController:NavHostController,
    defaultBottom:(Boolean) -> Unit,
    click:() -> Unit
) {

//    val regViewModel = hiltViewModel<RegisterViewModel>()
//    val loginViewModel = hiltViewModel<LoginViewModel>()
//    val optViewModel = hiltViewModel<OTPViewModel>()
    val profileViewModel = hiltViewModel<ProfilViewModel>()

    val login = remember {
        mutableStateOf(false)
    }

    val entering = remember {
        mutableStateOf(true)
    }

    val enter = remember {
        mutableStateOf(false)
    }


    AnimatedNavHost(navController = navController, startDestination = Routes.HomeAntrian.route ) {

        composable(Routes.HomeAntrian.route,
            enterTransition = {
                slideIntoContainer(towards = if (enter.value) AnimatedContentScope.SlideDirection.Right  else AnimatedContentScope.SlideDirection.Left ,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else if (entering.value) AnimatedContentScope.SlideDirection.Right else AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
            defaultBottom.invoke(true)
            AntrianScreen(
                dark,
                navController
            ) {
                click.invoke()
            }
        }

        composable(Routes.ScheduleAntrian.route,
            enterTransition = {
                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Right,tween(300))
            },
            exitTransition = {

                enter.value = true
                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Right,tween(300))
            }) {
            JadwalScreen(navController = navController,dark = dark)
        }

        composable(Routes.Profile.route,
            enterTransition = {
                slideIntoContainer(towards =   AnimatedContentScope.SlideDirection.Right ,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards =  AnimatedContentScope.SlideDirection.Right,tween(300))
            }) {
            ProfilScreen(navController = navController,dark = dark, viewModel = profileViewModel)
        }

        composable(Routes.FiturRoute.route+"/{route}",
            enterTransition = {
                fadeIn(tween(700))
            },
            arguments = listOf(navArgument("route") {
                NavType.StringType
            })
        ) {
            defaultBottom.invoke(false)
            MainFitur(it.arguments?.getString("route")!!,dark = dark, click = {
                click.invoke()
            })
        }

    }
}