package com.example.aplikasiklinik.view.currentantrian

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.utils.TokenManager
import com.example.aplikasiklinik.utils.networkChecker
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.currentantri.ShimeringCurrentAntri
import kotlinx.coroutines.launch
import kotlin.math.log

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CurrentAntrian(
    navController: NavController,
    vm:CurrenAntrViewModel,
    loginVm:LoginViewModel
) {

    loginVm.getLoginStatus()
    val loginState = loginVm.uiState.collectAsState().value

    val isLoadingBatal = remember {
        mutableStateOf(false)
    }

    val isErrorBatal = remember {
        mutableStateOf(false)
    }

    val confirmDialog = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current

    ConfirmDialog(title = "Apakah kamu yakin untuk membatalkan antrian ?", icon = R.drawable.warning,
        boolean = confirmDialog , cancel = { confirmDialog.value = false }) {
        confirmDialog.value = false
        vm.batalAntrian(
            isLoadingBatal,
            isErrorBatal
        ) {
            Toast.makeText(context,"Berhasil membatalkan antrian",Toast.LENGTH_LONG).show()
            navController.navigate(Routes.Home.route+"/${Routes.HomeAntrian.route}") {
                popUpTo(0)
            }
        }
    }

    val state = rememberScrollState()
    Log.d("TOKENNYA: ",TokenManager(context).getToken()!!)
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

    val isLoading = remember {
        mutableStateOf(true)
    }

    val isError = remember {
        mutableStateOf(false)
    }

    vm.getCurrentAntri(isLoading,isError)
    val uiState = vm.uiState.collectAsState().value

    ModalBottomSheetLayout(sheetContent = {
        BottomConnectionWarning(
            context = context ,
            iconClick = {
                coroutineScope.launch {
                    networkConnection.value = networkChecker(context)
                    if (!networkConnection.value) {
                        Toast.makeText(context,"Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                    }
                }

            }) {
            coroutineScope.launch {
                networkConnection.value = networkChecker(context)
                if (!networkConnection.value) {
                    Toast.makeText(context,"Tidak ada koneksi internet", Toast.LENGTH_SHORT).show()
                }
            }


        }
    },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        scrimColor = Color.Black.copy(0.8F),
        sheetElevation = 0.dp) {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                CustomTopBar(navController, stringResource(R.string.antrian_sekarang))
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
                  if (uiState.kurang_antrian != null) {
                      Surface(
                          color = MaterialTheme.colors.onBackground,
                          shape = RoundedCornerShape(20.dp),
                          modifier = Modifier
                              .padding(start = 14.dp, end = 14.dp)
                      ) {
                          Column {
                              Row(
                                  verticalAlignment = Alignment.CenterVertically,
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(
                                          start = 14.dp,
                                          end = 14.dp,
                                          top = 14.dp,
                                          bottom = 14.dp
                                      )
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.profile_menu_icon),
                                      contentDescription = null,
                                      tint = MaterialTheme.colors.primaryVariant,
                                      modifier = Modifier
                                          .size(16.dp)
                                  )
                                  Spacer(modifier = Modifier.width(12.dp))
                                  Text(
                                      text = loginState.first().name,
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
                          Box(
                              modifier = Modifier
                                  .fillMaxWidth()
                                  .wrapContentWidth(CenterHorizontally)
                                  .padding(14.dp)
                          ) {
                              Text(
                                  text = if (uiState.no_bpjs != null) stringResource(R.string.pasien_status) else "Pasien Umum",
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
                                  .wrapContentWidth(CenterHorizontally)
                                  .padding(14.dp),
                              horizontalAlignment = CenterHorizontally
                          ) {
                              Surface(
                                  color = Color.Transparent
                              ) {
                                  Text(
                                      text = uiState.nomer_antrian!!.toString(),
                                      style = MaterialTheme.typography.h1,
                                      color = MaterialTheme.colors.surface,
                                      fontSize = 48.sp,
                                  )
                              }
                              Spacer(modifier = Modifier.height(4.dp))
                              Box(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                                  Divider(
                                      color = Color.Black.copy(0.2f),
                                      modifier = Modifier
                                          .width(20.dp)
                                  )
                              }
                              Spacer(modifier = Modifier.height(16.dp))
                              Text(
                                  text = if (uiState.kurang_antrian!! > 1) "Kurang ${uiState.kurang_antrian} antrian" else "Yuk segera persiapan, giliran kamu !",
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
                          Column {
                              Row(
                                  verticalAlignment = Alignment.CenterVertically,
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(start = 14.dp, end = 14.dp, top = 14.dp)
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.clock_icon),
                                      contentDescription = null,
                                      tint = MaterialTheme.colors.primaryVariant,
                                      modifier = Modifier
                                          .size(16.dp)
                                  )
                                  Spacer(modifier = Modifier.width(12.dp))
                                  Text(
                                      text = uiState.data?.jam_antri!!,
                                      style = MaterialTheme.typography.body1,
                                      color = MaterialTheme.colors.primaryVariant
                                  )
                              }
                              Spacer(modifier = Modifier.height(20.dp))
                              Row(
                                  verticalAlignment = Alignment.CenterVertically,
                                  modifier = Modifier
                                      .fillMaxWidth()
                                      .padding(start = 14.dp, end = 14.dp, bottom = 14.dp)
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.status_icon),
                                      contentDescription = null,
                                      tint = MaterialTheme.colors.primaryVariant,
                                      modifier = Modifier
                                          .size(16.dp)
                                  )
                                  Spacer(modifier = Modifier.width(12.dp))
                                  Text(
                                      text = uiState.data?.status!!,
                                      style = MaterialTheme.typography.body1,
                                      color = MaterialTheme.colors.primaryVariant
                                  )
                              }
                          }

                      }
                      Spacer(modifier = Modifier.height(20.dp))
                      Box(modifier = Modifier
                          .padding(start = 14.dp, end = 14.dp)) {
                          ButtonClick(color = MaterialTheme.colors.error,
                              text = stringResource(R.string.batal_antri),
                              modifier = Modifier
                                  .fillMaxWidth()) {
                            confirmDialog.value = true
                          }
                      }
                  } else {
                      ShimeringCurrentAntri()
                  }
                }
            }
        }
    }


}



