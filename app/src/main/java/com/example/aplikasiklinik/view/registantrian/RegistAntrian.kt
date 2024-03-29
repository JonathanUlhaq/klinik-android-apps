package com.example.aplikasiklinik.view.registantrian

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.utils.networkChecker
import com.example.aplikasiklinik.view.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegistAntrian(
    navController: NavController,
    vm:RegisAntriViewModel
) {
    val dropDown = remember {
        mutableStateOf(false)
    }

    val keluhan = remember {
        mutableStateOf("")
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val alergi = remember {
        mutableStateOf("")
    }

    val focusKeluhan = remember {
        FocusRequester()
    }
    val poli = remember {
        mutableStateOf("")
    }

    val focusAlergi = remember {
        FocusRequester()
    }
    LoadingScreen(boolean = isLoading.value)

    val state = rememberScrollState()
    val context = LocalContext.current
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { false })
    val coroutineScope = rememberCoroutineScope()
    val networkConnection = remember {
        mutableStateOf(networkChecker(context))
    }


        if (!networkConnection.value) {
            LaunchedEffect(key1 = Unit) {
                sheetState.show()
            }
        } else {
            LaunchedEffect(key1 = Unit) {
                sheetState.hide()
            }
        }

    val isError = remember {
        mutableStateOf(false)
    }

    val isEmpty = remember {
        mutableStateOf(false)
    }





    val iconDrop by animateIntAsState(targetValue = if (dropDown.value) R.drawable.arrow_down else R.drawable.arrow_right)


    ModalBottomSheetLayout(sheetContent = {
        BottomConnectionWarning(
            context = context ,
            iconClick = {
                coroutineScope.launch {
                    networkConnection.value = networkChecker(context)
                    if (!networkConnection.value) {
                        Toast.makeText(context,"Tidak ada koneksi internet",Toast.LENGTH_SHORT).show()
                    }
                }

            }) {
            coroutineScope.launch {
                networkConnection.value = networkChecker(context)
                if (!networkConnection.value) {
                    Toast.makeText(context,"Tidak ada koneksi internet",Toast.LENGTH_SHORT).show()
                }
            }


        }
    },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Black.copy(0.6F),
        sheetElevation = 0.dp) {
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
                    ) {
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
                                ButtonDropDown(dropDown, poli, iconDrop) {
                                    focusKeluhan.requestFocus()
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                TextArea(
                                    value = keluhan,
                                    label = stringResource(R.string.keluhan),
                                    modifier = Modifier.focusRequester(focusKeluhan)
                                ) {
                                    focusAlergi.requestFocus()
                                }
                                Spacer(modifier = Modifier.height(20.dp))
                                TextArea(
                                    value = alergi,
                                    label = stringResource(R.string.alergi),
                                    modifier = Modifier.focusRequester(focusAlergi)
                                ) {

                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                AnimatedVisibility(visible = isError.value) {
                                    Text(
                                        "* Anda sudah mengantri",
                                        fontSize = 10.sp,
                                        color = Color.Black.copy(0.8f),
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                                AnimatedVisibility(visible = isEmpty.value) {
                                    Text(
                                        "* Mohon isi semua form",
                                        fontSize = 10.sp,
                                        color = Color.Black.copy(0.8f),
                                        style = MaterialTheme.typography.caption
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(
                            modifier = Modifier
                                .padding(start = 14.dp, end = 14.dp)
                                .imePadding()
                        ) {
                            ButtonClick(
                                color = MaterialTheme.colors.primary,
                                text = stringResource(id = R.string.daftar),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                if (keluhan.value.isNotEmpty() &&
                                        alergi.value.isNotEmpty() &&
                                        poli.value.isNotEmpty()) {
                                    vm.daftarAntrian(context,isEmpty,isError,boolean = isLoading,alergi = alergi.value, keluhan = keluhan.value, poli = poli.value) {
                                        it.let { res ->
                                            navController.navigate(Routes.CurrentAntrian.route) {
                                                popUpTo(0)
                                            }
                                        }
                                    }
                                } else {
                                    isEmpty.value  = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}


