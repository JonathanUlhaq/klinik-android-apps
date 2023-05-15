package com.example.aplikasiklinik.view.otp

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.BottomConnectionWarning
import com.example.aplikasiklinik.utils.CountDownResendOTP
import com.example.aplikasiklinik.utils.networkChecker
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.otp.OTPForm
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OtpScreen(
    dark: Boolean,
    navController: NavController,
    mainVm: MainActivityViewModel,
    viewModel: OTPViewModel
) {

    val systemUiController = rememberSystemUiController()
    if (dark) {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.background
        )
    } else {
        systemUiController.setStatusBarColor(
            color = MaterialTheme.colors.background
        )
    }
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })
    val coroutineScope = rememberCoroutineScope()
    val scrollable = rememberScrollState()

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
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                TopAppBar(
                    title = {

                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            navController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.back_arrow),
                                "backIcon",
                                modifier = Modifier
                                    .size(20.dp),
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.background,
                    contentColor = Color.White,
                    elevation = 0.dp
                )
            }
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
                                text = stringResource(id = R.string.verifikasi_title),
                                style = MaterialTheme.typography.h2,
                                color = MaterialTheme.colors.surface
                            )

                            Spacer(modifier = Modifier.weight(1F))

                            Image(
                                painter = painterResource(id = if (dark) R.drawable.dark_verifikasi_image else R.drawable.verifikasi_image),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(112.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))
                        Text(
                            text = stringResource(id = R.string.verifikasi_desc),
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
                                    .offset(y = 65.dp)
                                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                    .verticalScroll(state = scrollable, enabled = true)

                            ) {

                                OTPForm(
                                    event = {
                                        if (networkChecker(context)) {
                                            navController.navigate(Routes.Home.route) {
                                                popUpTo(0)
                                            }
                                        } else {
                                            coroutineScope.launch {
                                                sheetState.show()
                                            }
                                        }
                                    }
                                ) { otpValue ->
                                    viewModel.otpValue.value = otpValue
                                }
                                Spacer(
                                    modifier = Modifier
                                        .height(14.dp)
                                )
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(CenterHorizontally)) {
                                    CountDownResendOTP(timer = 30, modifier = Modifier.imePadding())
                                }
                                Spacer(
                                    modifier = Modifier
                                        .height(14.dp)
                                )
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
                                    painter = painterResource(id = R.drawable.verifikasi_icon),
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

