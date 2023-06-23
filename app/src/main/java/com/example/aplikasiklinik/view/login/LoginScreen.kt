package com.example.aplikasiklinik.view.login

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.utils.SharePrefrence
import com.example.aplikasiklinik.utils.networkChecker
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LoginScreen(
    navController: NavController,
    dark: Boolean,
    viewModel: LoginViewModel
) {


    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )

    val phone = remember {
        mutableStateOf("")
    }

    val firstString = remember {
        mutableStateOf("")
    }

    val detectFirst = remember {
        mutableStateOf(false)
    }



    if (phone.value.isNotEmpty()) {
        firstString.value = phone.value.first().toString()
        detectFirst.value = firstString.value == "0"
    }

    val isLoading = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }

    val visibile = remember {
        mutableStateOf(false)
    }

    LoadingScreen(boolean = isLoading.value)

    val scrollable = rememberScrollState()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            BottomConnectionWarning(
                context = context,
                iconClick = {
                    coroutineScope.launch {
                        sheetState.hide()
                    }
                }) {
                coroutineScope.launch {
                    sheetState.hide()
                }

            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Black.copy(0.6F),
        sheetElevation = 0.dp
    ) {
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
                                painter = painterResource(id = if (dark) R.drawable.dark_login_image else R.drawable.login_image),
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

                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    DisableInput()
                                    Spacer(modifier = Modifier.width(8.dp))
                                    OutlinedTextFields(
                                        phone,
                                        label = stringResource(id = R.string.phone),
                                        R.drawable.phone_icon,
                                        eventFocus = {
//                                    hide.value = true
                                        },
                                        keyboardType = KeyboardType.Number,
                                        trailingIcon = {
                                            AnimatedVisibility(visible = (phone.value.isNotEmpty())) {
                                                IconButton(onClick = { phone.value = "" }) {
                                                    Icon(
                                                        painter = painterResource(id = R.drawable.close_icon),
                                                        contentDescription = null,
                                                        tint = Color.White,
                                                        modifier = Modifier
                                                            .size(16.dp)
                                                    )
                                                }
                                            }
                                        },
                                        isPhone = true,
                                        error = isError.value
                                    ) {
//                                hide.value = false
                                    }
                                }

                                Spacer(modifier = Modifier.height(2.dp))
                                AnimatedVisibility(visible = isError.value) {
                                    Text(
                                        "* Nomor telepon tidak terdaftar",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colors.error,
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                AnimatedVisibility(visible = detectFirst.value) {
                                    Text(
                                        "* Isiikan nomor telepon setelah angka 0",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colors.error,
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                AnimatedVisibility(visible = phone.value.length < 10 && phone.value.isNotEmpty()) {
                                    Text(
                                        "* Isiikan nomor telepon minimal 10 digit",
                                        fontSize = 12.sp,
                                        color = MaterialTheme.colors.error,
                                        style = MaterialTheme.typography.caption
                                    )
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
                                    Text(text = " " + stringResource(id = R.string.daftar),
                                        color = MaterialTheme.colors.surface.copy(0.7F),
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate(Routes.Register.route)
                                            })
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                VerificationButton {
                                    if (!networkChecker(context)) {
                                        coroutineScope.launch {
                                            sheetState.show()
                                        }
                                    } else {
                                        if (phone.value.isNotEmpty() && !detectFirst.value && phone.value.length > 10) {
                                            viewModel.getOtp(isError,isLoading,phone.value.trim())
                                            {
                                                viewModel.addNumber(phone.value.trim())
                                                navController.navigate(Routes.Otp.route)
                                            }
                                        } else {
                                            isError.value = true
                                        }
//                                        if (phone.value.isNotEmpty()) {
//                                            viewModel.loginPasien(
//                                                isError,
//                                                isLoading,
//                                                phone.value.trim()) { user, token ->
//                                                if (user != null) {
//                                                    viewModel.addLoginStatus(
//                                                        LoginSaver(
//                                                            id = 0,
//                                                            name = user.name!!,
//                                                            telepon = user.telepon!!,
//                                                            alamat = user.alamat!!,
//                                                            jenis_kelamin = "",
//                                                            foto = "",
//                                                            no_bpjs = "",
//                                                            tanggal_lahir = user.tanggal_lahir!!
//                                                        )
//                                                    )
//                                                    viewModel.addToken(token!!)
//                                                    navController.navigate(Routes.Otp.route)
//                                                }
//
//                                            }
//                                        } else {
//                                            isError.value = true
//                                        }
                                    }

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
}