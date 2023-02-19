package com.example.aplikasiklinik.view.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.OutlinedTextFields
import com.example.aplikasiklinik.components.VerificationButton
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.register.RegisForm
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun LoginScreen(
    navController: NavController,
    mainVm:MainActivityViewModel,
    viewModel:LoginViewModel
) {

    val darkMode = mainVm.uiState.collectAsState().value.first().darkMode
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val scrollable = rememberScrollState()

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
        Surface(
            color = Color.Transparent,
            modifier = Modifier
                .padding(it)
        ) {

            Column {
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
                                text = stringResource(id = R.string.login_title),
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.surface
                            )

                            Spacer(modifier = Modifier.weight(1F))

                            Image(
                                painter = painterResource(id = if (darkMode) R.drawable.dark_login_image else R.drawable.login_image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(112.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(id = R.string.login_desc),
                            style = MaterialTheme.typography.body1,
                            color = MaterialTheme.colors.surface
                        )

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

                        ) {

                            OutlinedTextFields(
                                viewModel.phone,
                                label = stringResource(id = R.string.phone),
                                R.drawable.phone_icon,
                                eventFocus = {
//                                    hide.value = true
                                },
                                keyboardType = KeyboardType.Number
                            ) {
//                                hide.value = false
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Text(
                                    text = stringResource(id = R.string.belum_punya_akun),
                                    color = MaterialTheme.colors.onSurface,
                                    style = MaterialTheme.typography.body2
                                )
                                Text(text = " "+stringResource(id = R.string.daftar),
                                    color = MaterialTheme.colors.onPrimary,
                                    style = MaterialTheme.typography.body2,
                                    modifier = Modifier
                                        .clickable { navController.navigate(Routes.Register.route) })
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                            VerificationButton {
                                navController.navigate(Routes.Otp.route)
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
                                painter = painterResource(id = R.drawable.login_icon),
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