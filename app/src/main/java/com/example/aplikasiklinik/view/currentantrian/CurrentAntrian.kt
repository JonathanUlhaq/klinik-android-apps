package com.example.aplikasiklinik.view.currentantrian

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.ButtonClick
import com.example.aplikasiklinik.components.CustomTopBar
import com.example.aplikasiklinik.components.FiturHeader

@Composable
fun CurrentAntrian(
    navController: NavController
) {
    val state = rememberScrollState()
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            CustomTopBar(navController,"Antrian Sekarang")
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it)
                .verticalScroll(state),
            color = Color.Transparent
        ) {
            Column {
                FiturHeader()
                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally)
                            .padding(14.dp)
                    ) {
                        Text(
                            text = "Pasien BPJS",
                            style = MaterialTheme.typography.h1,
                            fontSize = 16.sp,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.name_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primaryVariant,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "Ragnar Holbrok",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.primaryVariant
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.bpjs_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primaryVariant,
                                modifier = Modifier
                                    .size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "3351232132",
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.primaryVariant
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally)
                            .padding(14.dp),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "Pasien BPJS",
                            style = MaterialTheme.typography.h1,
                            fontSize = 14.sp,
                            color = MaterialTheme.colors.primaryVariant
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            shape = CircleShape,
                            border = BorderStroke(6.dp, MaterialTheme.colors.primaryVariant),
                            color = MaterialTheme.colors.onBackground
                        ) {
                            Text(
                                text = "10",
                                style = MaterialTheme.typography.h1,
                                color = MaterialTheme.colors.surface,
                                fontSize = 48.sp,
                                modifier = Modifier
                                    .padding(14.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Kurang 1 antrian",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = MaterialTheme.colors.onBackground,
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.date_icon_svg),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primaryVariant,
                            modifier = Modifier
                                .size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "06/6/666",
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.primaryVariant
                        )
                    }
                }
                Spacer(modifier = Modifier.height(26.dp))
               Box(modifier = Modifier
                   .padding(start = 14.dp, end = 14.dp)) {
                   ButtonClick(color = MaterialTheme.colors.error,
                       text = "Batal Antri" ) {

                   }
               }
            }
        }
    }
}



