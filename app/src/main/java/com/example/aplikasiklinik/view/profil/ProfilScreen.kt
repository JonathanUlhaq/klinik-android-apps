package com.example.aplikasiklinik.view.profil

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.DatePicker
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfilScreen(
    dark: Boolean,
    viewModel: ProfilViewModel,
    navController: NavController
) {

    val dateColor by animateColorAsState(targetValue = if (viewModel.edit.value) MaterialTheme.colors.surface else MaterialTheme.colors.surface.copy(0.3F))
    val dateBorderColor by animateColorAsState(targetValue = if (viewModel.edit.value) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.primaryVariant.copy(0.5F) )


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
                        .height(150.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                            .height(120.dp)
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.onPrimary)
                    )
                    Column(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .fillMaxWidth()
                            .wrapContentWidth(CenterHorizontally),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Ragnar Holbrook",
                            style = MaterialTheme.typography.h1,
                            color = MaterialTheme.colors.onSurface
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Surface(
                            color = MaterialTheme.colors.onPrimary,
                            border = BorderStroke(7.dp, MaterialTheme.colors.background),
                            shape = CircleShape,
                            elevation = 0.dp
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.profile_menu_icon),
                                contentDescription = null,
                                tint = MaterialTheme.colors.onSurface,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(46.dp)
                            )
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
                    ProfilContent(R.drawable.nik_icon, viewModel.nik,viewModel.edit.value,
                        KeyboardType.NumberPassword)

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(R.drawable.name_icon, viewModel.name,viewModel.edit.value,
                        KeyboardType.Text)

                    Spacer(modifier = Modifier.height(20.dp))
                    DatePicker(context = current , date = viewModel.date, color = dateBorderColor, colorSecond = dateColor, viewModel.edit.value)

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(R.drawable.location_icon, viewModel.address,viewModel.edit.value,
                        KeyboardType.Text)

                    Spacer(modifier = Modifier.height(20.dp))
                    ProfilContent(R.drawable.phone_icon, viewModel.phone,viewModel.edit.value,
                        KeyboardType.Phone)
                    Spacer(modifier = Modifier.height(15.dp))
                }
            }
        }
    }
}

@Composable
fun ProfilContent(
    icon: Int,
    desc: MutableState<String>,
    boolean: Boolean,
    keyboard:KeyboardType
) {

    OutlinedTextField(value = desc.value,
        onValueChange = { desc.value = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.surface,
            backgroundColor = Color.Transparent,
            disabledBorderColor = MaterialTheme.colors.primaryVariant.copy(0.5F),
            unfocusedBorderColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedBorderColor = MaterialTheme.colors.primaryVariant,
            unfocusedLabelColor = MaterialTheme.colors.primaryVariant.copy(0.8F),
            focusedLabelColor = MaterialTheme.colors.primaryVariant,
            leadingIconColor = MaterialTheme.colors.primaryVariant,
            disabledLeadingIconColor = MaterialTheme.colors.primaryVariant.copy(0.5F)
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
        },
        enabled = boolean,
        keyboardOptions = KeyboardOptions(keyboardType = keyboard),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    )

//    Row(
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
//            painter = painterResource(id = icon),
//            contentDescription = null,
//            tint = MaterialTheme.colors.primaryVariant,
//            modifier = Modifier
//                .size(30.dp)
//        )
//        Spacer(modifier = Modifier.width(20.dp))
//        Text(
//            text = desc,
//            style = MaterialTheme.typography.body1,
//            color = MaterialTheme.colors.surface
//        )
//    }
}

@Composable
fun EditButton(boolean: Boolean,click: () -> Unit) {

    val icons by animateIntAsState(targetValue = if (boolean) R.drawable.save_icon else R.drawable.edit_icon)
    val colors by animateColorAsState(targetValue = if (boolean) MaterialTheme.colors.secondary else MaterialTheme.colors.primary)

    Button(
        onClick = { click.invoke() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colors,
            contentColor = MaterialTheme.colors.onSurface
        ),
        shape = RoundedCornerShape(15.dp),
        elevation = ButtonDefaults.elevation(0.dp)
    ) {
        Icon(
            painter = painterResource(id = icons),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = if (boolean) "Simpan" else "Ubah",
            style = MaterialTheme.typography.body2
        )
    }
}



