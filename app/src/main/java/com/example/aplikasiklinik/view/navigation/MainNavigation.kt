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
import com.example.aplikasiklinik.view.home.HomeMenu
import com.example.aplikasiklinik.view.jadwal.JadwalScreen
import com.example.aplikasiklinik.view.login.LoginScreen
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
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
fun MainNavigation(
    dark:Boolean,
    navMain:NavHostController,
    click:()->Unit
) {


    val regViewModel = hiltViewModel<RegisterViewModel>()
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val optViewModel = hiltViewModel<OTPViewModel>()
    val mainVm = hiltViewModel<MainActivityViewModel>()

    val login = remember {
        mutableStateOf(false)
    }

    val boardingExit = remember {
        mutableStateOf(false)
    }

    AnimatedNavHost(navController = navMain, startDestination = Routes.Splash.route ) {
        composable(Routes.Splash.route,
            enterTransition = {
                fadeIn(tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
            SplashScreen(navMain)
        }

        composable(Routes.Onboarding.route,
            enterTransition = {
                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (boardingExit.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Down,tween(300))
            }) {
            OnBoardingScreen(navMain,mainVm) {
                login.value = it
                boardingExit.value = it
            }
        }

        composable(Routes.Register.route,
            enterTransition = {
                slideIntoContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Down else AnimatedContentScope.SlideDirection.Right,tween(300))
            }) {
            RegisterScreen(navMain,regViewModel,mainVm) {
                login.value = it
            }
        }


        composable(Routes.Login.route,
            enterTransition = {
                slideIntoContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Down else if (!boardingExit.value) AnimatedContentScope.SlideDirection.Down else AnimatedContentScope.SlideDirection.Right,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
            LoginScreen(navController = navMain,mainVm,loginViewModel)
        }

        composable(Routes.Otp.route,
            enterTransition = {
                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Left ,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
            OtpScreen(dark,navMain,mainVm,OTPViewModel())
        }

        composable(Routes.Home.route,
            enterTransition = {
                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Left ,tween(300))
            },
            exitTransition = {
                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
            }) {
           HomeMenu(dark = dark ) {
               click.invoke()
           }
        }
    }
}