package com.example.aplikasiklinik.view.navigation

import android.net.Uri
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.aplikasiklinik.view.antrianhistory.AntrianHistory
import com.example.aplikasiklinik.view.currentantrian.CurrenAntrViewModel
import com.example.aplikasiklinik.view.currentantrian.CurrentAntrian
import com.example.aplikasiklinik.view.home.HomeMenu
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.profil.DetailProfile
import com.example.aplikasiklinik.view.registantrian.RegisAntriViewModel
import com.example.aplikasiklinik.view.registantrian.RegistAntrian
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentNavigation(
    route: String,
    antrian: Int,
    dark: Boolean,
    click: () -> Unit,
    uri: Uri,
    navController: NavHostController
) {

    val regisVm = hiltViewModel<RegisAntriViewModel>()
    val curVm = hiltViewModel<CurrenAntrViewModel>()
    val loginVm = hiltViewModel<LoginViewModel>()
    AnimatedNavHost(navController = navController, startDestination = route) {

        composable(
            Routes.CurrentAntrian.route,
//            enterTransition = {
//                fadeIn(tween(700))
//            }
        ) {
            CurrentAntrian(navController, curVm, loginVm)
        }

        composable(Routes.AntrianHistory.route,
//            enterTransition = {
//                fadeIn(tween(700))
//            }
        ) {
            AntrianHistory(navController)
        }

        composable(Routes.AntrianRegister.route,
//            enterTransition = {
//                fadeIn(tween(700))
//            }
        ) {
            RegistAntrian(navController, regisVm)
        }

        composable(Routes.Home.route + "/{route}",
//            enterTransition = {
//                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left, tween(300))
//            },
//            exitTransition = {
//                fadeOut(tween(700))
//            },
//            arguments = listOf(navArgument("route") {
//                NavType.StringType
//            }
//            )
        ) {
            HomeMenu(
                dark = dark,
                route = it.arguments?.getString("route")!!,
                antrian = antrian,
                uri = uri
            ) {
                click.invoke()
            }
        }

        composable(Routes.DetailProfile.route,
//            enterTransition = {
//                fadeIn(tween(700))
//            }
        ) {
            DetailProfile(navController)
        }


    }
}