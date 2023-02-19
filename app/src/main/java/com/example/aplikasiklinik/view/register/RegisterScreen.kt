package com.example.aplikasiklinik.view.register

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.VerificationButton
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.register.RegisForm
import com.example.aplikasiklinik.widget.register.RegisterViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel:RegisterViewModel,
    mainVm:MainActivityViewModel,
    login:(Boolean) -> Unit
) {

    val darkMode = mainVm.uiState.collectAsState().value.first().darkMode
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val scrollable = rememberScrollState()
    val context = LocalContext.current

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .padding(it)
        ) {

            Column {
                AnimatedVisibility(visible = !viewModel.hide.value) {
                    Column(
                        modifier = Modifier
                            .padding(start = 14.dp, end = 14.dp, top = 14.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.Bottom
                        ) {
                            Text(
                                text = stringResource(id = R.string.register_title),
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.surface
                            )

                            Spacer(modifier = Modifier.weight(1F))

                            Image(
                                painter = painterResource(id = if (darkMode) R.drawable.dark_register_image else  R.drawable.register_image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(112.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(id = R.string.register_desc),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface
                        )

                    }
                }
                Spacer(modifier = Modifier.height(14.dp))

                Box {
                    Surface(
                        color = MaterialTheme.colors.primary,
                        shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .offset(y = 28.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .offset(y = 35.dp)
                                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                .verticalScroll(state = scrollable, enabled = true)
                                .fillMaxHeight()
                                .wrapContentHeight(CenterVertically),
                        ) {
                            RegisForm(viewModel.nik, viewModel.hide, viewModel.name, context, viewModel.date, viewModel.address, viewModel.phone) {
                                login.invoke(true)
                                navController.navigate(Routes.Login.route)
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            VerificationButton {
                                login.invoke(false)
                                navController.navigate(Routes.Login.route)
                            }

                            Spacer(
                                modifier = Modifier
                                    .height(70.dp)
                                    .imePadding()
                            )

                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentSize(Alignment.Center)
                    ) {
                        Surface(
                            color = Color.White,
                            border = BorderStroke(5.dp, MaterialTheme.colors.primary),
                            shape = CircleShape
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.register_icon),
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(20.dp)
                                    .size(30.dp),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
            }

        }
    }
}





