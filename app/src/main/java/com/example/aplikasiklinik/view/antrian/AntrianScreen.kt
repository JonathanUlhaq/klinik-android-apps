package com.example.aplikasiklinik.view.antrian

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.utils.NotificationManager
import com.example.aplikasiklinik.widget.antrian.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AntrianScreen(
    dark: Boolean,
    antiran:Int,
    navController: NavController,
    click: () -> Unit
) {

//    Nanti dihapus
    val antrian = remember {
        mutableStateOf(antiran)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        CoroutineScope(Dispatchers.IO).launch {
            if (antrian.value <= 2 ) {
                val notif = NotificationManager(context,"Persiapan, antrian kurang ${antrian.value} orang lagi")
                notif.notificationManager()
            }
        }
    } )



    val systemUiController = rememberSystemUiController()
    if(dark){
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.onPrimary
        )

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colors.onPrimary
        )
    }else {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.primaryVariant
        )

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colors.background
        )
    }

    val scrollState = rememberScrollState()
    val state = rememberPagerState()
    LaunchedEffect(Unit) {
        while (true){
            yield()
            delay(3000)
            with(state) {
                val target = if (currentPage < pageCount - 1) currentPage + 1 else 0

                tween<Float>(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
                animateScrollToPage(page = target)
            }
        }


    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .padding(it)
        ) {
            Column {
                Box {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                            .height(170.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onPrimary)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 14.dp, end = 14.dp, top = 14.dp)
                    ) {
                        AntrianTitleHeader(
                            dark
                        ) {
                            click.invoke()
                        }
                        Spacer(
                            modifier = Modifier
                                .height(20.dp)
                        )
                        WidgetInformation(antrian.value) {
                            antrian.value = antrian.value - 1
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .verticalScroll(scrollState, enabled = true)
                    ,
                    color = Color.Transparent
                ) {
                    Column {
                        WidgetAnnouncement(state)
                        Spacer(modifier = Modifier.height(10.dp))
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 14.dp),
                            color = MaterialTheme.colors.onBackground,
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            AntrianListMenu(navController)
                        }
                    }
                }

            }
        }
    }
}














