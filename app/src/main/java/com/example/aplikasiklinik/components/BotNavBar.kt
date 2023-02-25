package com.example.aplikasiklinik.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.view.navigation.Routes

@Composable
fun BotNavBar(
    navController: NavController
) {

    val menu = listOf(
        Routes.HomeAntrian,
        Routes.ScheduleAntrian,
        Routes.Profile
    )

    val title = listOf(
        stringResource(id = R.string.antrian),
        stringResource(id = R.string.jam),
        stringResource(id = R.string.profile),
    )

    Surface(
        color = MaterialTheme.colors.onBackground,
        elevation = 10.dp,
        contentColor = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp, top = 14.dp, bottom = 14.dp)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val currentMenu = remember {
                mutableStateOf(false)
            }
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            menu.forEachIndexed { index, item ->
                currentMenu.value = currentRoute == item.route

                val colored by animateColorAsState(
                    targetValue =
                    if (currentMenu.value) MaterialTheme.colors.onSurface else MaterialTheme.colors.surface.copy(
                        0.4F
                    )
                )

                val backgroundColor by animateColorAsState(
                    targetValue =
                    if (currentMenu.value) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground
                )


                val sized by animateIntAsState(
                    targetValue =
                    if (currentMenu.value) 16 else 14
                )

                IconButton(onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { routes ->
                            popUpTo(routes) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }) {
                    Surface(
                        color = backgroundColor,
                        shape = RoundedCornerShape(50)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp, start = 16.dp, end = 16.dp),
                            verticalAlignment = CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.route,
                                modifier = Modifier
                                    .size(sized.dp),
                                tint = colored
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            AnimatedVisibility(visible = currentMenu.value) {
                                Text(
                                    text = title[index],
                                    style = MaterialTheme.typography.h1,
                                    fontSize = 12.sp,
                                    color = colored
                                )
                            }
                        }
                    }
                }

            }
        }
    }


}