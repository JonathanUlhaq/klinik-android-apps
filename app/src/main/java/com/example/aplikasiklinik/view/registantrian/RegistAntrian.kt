package com.example.aplikasiklinik.view.registantrian

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*

@Composable
fun RegistAntrian(
    navController: NavController
) {
    val dropDown = remember {
        mutableStateOf(false)
    }

    val keluhan = remember {
        mutableStateOf("")
    }

    val state = rememberScrollState()

    val iconDrop by animateIntAsState(targetValue = if (dropDown.value) R.drawable.arrow_down else R.drawable.arrow_right)
    val poli = remember {
        mutableStateOf("Pilih Poli")
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            CustomTopBar(navController, stringResource(R.string.pendaftaran_antrian))
        }
    ) {
        Surface(
            modifier = Modifier
                .padding(it),
            color = Color.Transparent
        ) {
            Column {
                FiturHeader()
                Column(
                    Modifier.verticalScroll(state)
                )  {
                    Surface(
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp),
                        color = MaterialTheme.colors.onBackground,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.regist_antrian_desc),
                                style = MaterialTheme.typography.h1,
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.surface
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            ButtonDropDown(dropDown, poli, iconDrop)
                            Spacer(modifier = Modifier.height(20.dp))
                            TextArea(
                                value = keluhan ,
                                label = stringResource(R.string.keluhan) ,
                                eventFocus = { /*TODO*/ }) {

                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            TextArea(
                                value = keluhan ,
                                label = stringResource(R.string.alergi) ,
                                eventFocus = { /*TODO*/ }) {

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .imePadding()) {
                        ButtonClick(color = MaterialTheme.colors.primary,
                            text = stringResource(id = R.string.daftar) ) {

                        }
                    }
                }
            }
        }
    }
}


