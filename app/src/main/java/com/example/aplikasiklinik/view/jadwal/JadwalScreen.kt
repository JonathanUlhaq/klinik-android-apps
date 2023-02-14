package com.example.aplikasiklinik.view.jadwal

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.widget.jadwal.ContentJadwalPraktik
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun JadwalScreen(
    dark: Boolean,
    navController: NavController
) {
    val systemUiController = rememberSystemUiController()
    if (dark) {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.onPrimary
        )

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colors.onPrimary
        )
    } else {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.primaryVariant
        )

        systemUiController.setNavigationBarColor(
            color = MaterialTheme.colors.background
        )
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
                Box(
                    modifier = Modifier
                        .height(150.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onPrimary)
                    )

                    Surface(
                        modifier = Modifier
                            .offset(y = 36.dp)
                            .padding(end = 20.dp)
                            .fillMaxWidth()
                            .height(120.dp),
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(40.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .padding( end = 16.dp)) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .wrapContentHeight(Alignment.Bottom)
                            ) {
                                Surface(
                                    color = MaterialTheme.colors.primary,
                                    border = BorderStroke(6.dp, MaterialTheme.colors.background),
                                    shape = CircleShape
                                ) {
                                    Icon(painter = painterResource(id = R.drawable.icon_praktik),
                                        contentDescription = null,
                                        tint = MaterialTheme.colors.onSurface,
                                        modifier = Modifier
                                            .padding(16.dp)
                                            .size(46.dp))
                                }
                            }
                            Spacer(modifier = Modifier.weight(1F))
                            Column {
                                Text(text = stringResource(R.string.jadwal_praktik_dokter),
                                    style = MaterialTheme.typography.h1,
                                    color = MaterialTheme.colors.onSurface)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(text = stringResource(R.string.dummy_desc_jadwal),
                                    style = MaterialTheme.typography.caption,
                                    color = MaterialTheme.colors.onSurface.copy(0.8F),
                                    textAlign = TextAlign.Justify,
                                    modifier = Modifier
                                        .width(180.dp))
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(CenterHorizontally)
                ) {
                    LazyColumn(content = {
                        items(3) {
                            ContentJadwalPraktik()
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    })
                }
            }
        }
    }
}



