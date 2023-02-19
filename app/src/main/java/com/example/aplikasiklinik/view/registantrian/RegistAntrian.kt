package com.example.aplikasiklinik.view.registantrian

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
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

    val buttonSize = remember {
        mutableStateOf(Size.Zero)}

    val typePoli = listOf(
        "Umum",
        "Gigi"
    )

    val state = rememberScrollState()

    val iconDrop by animateIntAsState(targetValue = if (dropDown.value) R.drawable.arrow_down else R.drawable.arrow_right)
    val poli = remember {
        mutableStateOf("Pilih Poli")
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        topBar = {
            CustomTopBar(navController, "Pendaftaran Antrian")
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
                                text = "Yuk lengkapi form dibawah ini agar kamu terdaftar sebagai antrian pasien online",
                                style = MaterialTheme.typography.h1,
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.surface
                            )
                            Spacer(modifier = Modifier.height(24.dp))
                            ButtonDropDown(dropDown, buttonSize, poli, iconDrop, typePoli)
                            Spacer(modifier = Modifier.height(20.dp))
                            TextArea(
                                value = keluhan ,
                                label = "Keluhan" ,
                                eventFocus = { /*TODO*/ }) {

                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            TextArea(
                                value = keluhan ,
                                label = "Alergi" ,
                                eventFocus = { /*TODO*/ }) {

                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .imePadding()) {
                        ButtonClick(color = MaterialTheme.colors.primary,
                            text = "Daftar" ) {

                        }
                    }
                }
            }
        }
    }
}


