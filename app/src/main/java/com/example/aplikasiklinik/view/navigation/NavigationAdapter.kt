package com.example.aplikasiklinik.view.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.aplikasiklinik.view.antrian.AntrianScreen
import com.example.aplikasiklinik.view.jadwal.JadwalScreen
import com.example.aplikasiklinik.view.login.LoginScreen
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.onboarding.OnBoardingScreen
import com.example.aplikasiklinik.view.otp.OTPViewModel
import com.example.aplikasiklinik.view.otp.OtpScreen
import com.example.aplikasiklinik.view.profil.ProfilScreen
import com.example.aplikasiklinik.view.register.RegisterScreen
import com.example.aplikasiklinik.view.splashscreen.SplashScreen
import com.example.aplikasiklinik.widget.register.RegisterViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.lifecycle.HiltViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationAdapter(
    dark:Boolean,
    navController:NavHostController,
    click:() -> Unit
) {


    val regViewModel = hiltViewModel<RegisterViewModel>()
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val optViewModel = hiltViewModel<OTPViewModel>()

    val login = remember {
        mutableStateOf(false)
    }

    val entering = remember {
        mutableStateOf(false)
    }

    val enterings = remember {
        mutableStateOf(false)
    }

    val enteringb = remember {
        mutableStateOf(false)
    }

    val enteringc = remember {
        mutableStateOf(false)
    }

    AnimatedNavHost(navController = navController, startDestination = Routes.HomeAntrian.route ) {

        composable(Routes.HomeAntrian.route,
            enterTransition = {
                enteringc.value = true
                slideIntoContainer(towards = if (enterings.value) AnimatedContentScope.SlideDirection.Right  else AnimatedContentScope.SlideDirection.Left ,tween(300))
            },
            exitTransition = {
                entering.value = false
                enteringb.value = false
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else if (entering.value) AnimatedContentScope.SlideDirection.Right else AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
            AntrianScreen(
                dark,
                navController
            ) {
                click.invoke()
            }
        }

        composable(Routes.ScheduleAntrian.route,
            enterTransition = {
                enteringb.value = true
                slideIntoContainer(towards =  if (entering.value) AnimatedContentScope.SlideDirection.Right else AnimatedContentScope.SlideDirection.Left ,tween(300))
            },
            exitTransition = {

                enterings.value = true
                slideOutOfContainer(towards = if (entering.value){
                    enteringb.value = false
                    AnimatedContentScope.SlideDirection.Right
                }
                else if  (!enteringb.value)  {
                    entering.value = false
                    enteringb.value = true
                    enteringc.value = false
                    AnimatedContentScope.SlideDirection.Left
                }
                else AnimatedContentScope.SlideDirection.Right,tween(300))
            }) {
            JadwalScreen(navController = navController)
        }

        composable(Routes.Profile.route,
            enterTransition = {
                slideIntoContainer(towards =  if (enteringc.value) AnimatedContentScope.SlideDirection.Left else AnimatedContentScope.SlideDirection.Right ,tween(300))
            },
            exitTransition = {
                entering.value = true
                enterings.value = true

                slideOutOfContainer(towards =  AnimatedContentScope.SlideDirection.Right,tween(300))
            }) {
            ProfilScreen(navController = navController)
        }

    }
}