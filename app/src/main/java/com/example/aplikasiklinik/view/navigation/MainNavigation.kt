package com.example.aplikasiklinik.view.navigation


import android.net.Uri
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.aplikasiklinik.view.home.HomeMenu
import com.example.aplikasiklinik.view.login.LoginScreen
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.onboarding.OnBoardingScreen
import com.example.aplikasiklinik.view.otp.OTPViewModel
import com.example.aplikasiklinik.view.otp.OtpScreen
import com.example.aplikasiklinik.view.register.RegisterScreen
import com.example.aplikasiklinik.view.splashscreen.SplashScreen
import com.example.aplikasiklinik.view.register.RegisterViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigation(
    dark:Boolean,
    startDest:String = Routes.Splash.route,
    antrian:Int,
    navMain:NavHostController,
    cameraClick:() -> Unit,
    uri: Uri,
    openCam:Boolean,
    contentCamera:@Composable () -> Unit = {},
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

    AnimatedNavHost(navController = navMain, startDestination = startDest ) {
        composable(Routes.Splash.route,
//            enterTransition = {
//                fadeIn(tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = AnimatedContentScope.SlideDirection.Left,tween(300))
//            }
        ) {
            SplashScreen(navMain,loginViewModel)
        }

        composable(Routes.Onboarding.route,
//            enterTransition = {
//                slideIntoContainer(towards = AnimatedContentScope.SlideDirection.Left,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (boardingExit.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Down,tween(300))
//            }
        ) {
            OnBoardingScreen(navMain,mainVm,dark) {
                login.value = it
                boardingExit.value = it
            }
        }

        composable(Routes.Register.route,
//            enterTransition = {
//                slideIntoContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Down else AnimatedContentScope.SlideDirection.Right,tween(300))
//            }
        ) {
            RegisterScreen(navMain,regViewModel,mainVm,loginViewModel,dark, cameraClick = {cameraClick.invoke()}, uri = uri, contentCamera = contentCamera, openCam = openCam) {
                login.value = it
            }
        }


        composable(Routes.Login.route,
//            enterTransition = {
//                slideIntoContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Down else if (!boardingExit.value) AnimatedContentScope.SlideDirection.Down else AnimatedContentScope.SlideDirection.Right,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
//            }
        ) {
            LoginScreen(navController = navMain,dark,loginViewModel)
        }

        composable(Routes.Otp.route,
//            enterTransition = {
//                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Left ,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
//            }
        ) {
            OtpScreen(dark,navMain,mainVm,optViewModel)
        }

        composable(Routes.Home.route,
//            enterTransition = {
//                slideIntoContainer(towards =  AnimatedContentScope.SlideDirection.Left ,tween(300))
//            },
//            exitTransition = {
//                slideOutOfContainer(towards = if (login.value) AnimatedContentScope.SlideDirection.Up else AnimatedContentScope.SlideDirection.Left,tween(300))
//            }
        ) {
           HomeMenu(dark = dark,antrian, uri = uri ) {
               click.invoke()
           }
        }
    }
}