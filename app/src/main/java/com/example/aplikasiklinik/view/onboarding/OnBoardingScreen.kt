package com.example.aplikasiklinik.view.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.PagerDot
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.ButtonClick
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.OnboardingContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavController,
    click:(Boolean) -> Unit
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val image = listOf(
        R.drawable.onboarding_a,
        R.drawable.onboarding_b,
        R.drawable.onboarding_c
    )

    val title = listOf(
        R.string.title_ob_a,
        R.string.title_ob_b,
        R.string.title_ob_c
    )

    val desc = listOf(
        R.string.desc_ob_a,
        R.string.desc_ob_b,
        R.string.desc_ob_c
    )

    val pagerState = rememberPagerState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .wrapContentSize(Center)

        ) {
            Column(
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically),
                horizontalAlignment = CenterHorizontally,
            ) {
                HorizontalPager(
                    pageCount = image.size,
                    state = pagerState,
                    modifier = Modifier) {index ->
                    OnboardingContent(
                        title = title[index],
                        image = image[index],
                        desc = desc[index]
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                PagerDot(3,pagerState.currentPage)
                Spacer(modifier = Modifier.height(60.dp))
                ButtonClick(color = MaterialTheme.colors.primary, text = stringResource(id = R.string.masuk) ) {
                    click.invoke(false)
                    navController.navigate(Routes.Login.route)
                }
                Spacer(modifier = Modifier.height(8.dp))
                ButtonClick(color = MaterialTheme.colors.secondary, text = stringResource(id = R.string.daftar) ) {
                    click.invoke(true)
                    navController.navigate(Routes.Register.route)
                }
            }
        }

    }

}



