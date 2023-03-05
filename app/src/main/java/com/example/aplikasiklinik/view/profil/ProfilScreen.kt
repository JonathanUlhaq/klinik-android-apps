package com.example.aplikasiklinik.view.profil

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.DatePicker
import com.example.aplikasiklinik.components.EditButton
import com.example.aplikasiklinik.widget.profil.ProfilContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfilScreen(
    dark: Boolean,
    viewModel: ProfilViewModel,
    navController: NavController
) {

    val dateColor by animateColorAsState(
        targetValue = if (viewModel.edit.value) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(
            0.7F
        )
    )
    val dateBorderColor by animateColorAsState(
        targetValue = if (viewModel.edit.value) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primaryVariant.copy(
            0.7F
        )
    )

    val current = LocalContext.current
    val scrollState = rememberScrollState()
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
                        .height(200.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                            .height(150.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onPrimary)
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally),
                        horizontalAlignment = CenterHorizontally
                    ) {
                        Text(
                            text = "Ragnar Holbrook",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                            Surface(
                                color = MaterialTheme.colors.onBackground,
                                border = BorderStroke(3.dp, MaterialTheme.colors.onPrimary),
                                shape = CircleShape
                            ) {
                                Surface(
                                    color = MaterialTheme.colors.onBackground,
                                    shape = CircleShape,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(120.dp)
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.foto_dummy),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(120.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                }

                            }

                    }
                }
                Spacer(modifier = Modifier.height(20.dp))

                Column(
                    modifier = Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .verticalScroll(scrollState),
                    horizontalAlignment = CenterHorizontally
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Start)
                    ) {
                        EditButton(viewModel.edit.value) {
                            viewModel.edit.value = !viewModel.edit.value
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(
                        R.drawable.nik_icon, viewModel.nik, viewModel.edit.value,
                        KeyboardType.NumberPassword
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(
                        R.drawable.name_icon, viewModel.name, viewModel.edit.value,
                        KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    DatePicker(
                        context = current,
                        date = viewModel.date,
                        color = dateBorderColor,
                        colorSecond = dateColor,
                        viewModel.edit.value
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(
                        R.drawable.location_icon, viewModel.address, viewModel.edit.value,
                        KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(
                        R.drawable.phone_icon, viewModel.phone, viewModel.edit.value,
                        KeyboardType.Phone
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}







