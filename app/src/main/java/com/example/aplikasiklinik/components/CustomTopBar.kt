package com.example.aplikasiklinik.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.view.navigation.Routes


@Composable
fun CustomTopBar(navController: NavController, title:String,route:String = Routes.Home.route+"/"+Routes.HomeAntrian.route ) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                color = MaterialTheme.colors.onSurface,
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(route) {
                    popUpTo(0)
                }
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface,
                    modifier = Modifier
                        .size(16.dp)
                )
            }
        },
        backgroundColor = MaterialTheme.colors.onPrimary,
        elevation = 0.dp
    )
}