package com.example.aplikasiklinik.view.profil

import android.net.Uri
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
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
import com.example.aplikasiklinik.components.ButtonClick
import com.example.aplikasiklinik.components.ConfirmDialog
import com.example.aplikasiklinik.components.DatePicker
import com.example.aplikasiklinik.components.EditButton
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.profil.ProfilContent
import com.example.aplikasiklinik.widget.profil.ProfileShimering
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ProfilScreen(
    dark: Boolean,
    viewModel: ProfilViewModel,
    loginVm:LoginViewModel,
    navController: NavController
) {
    loginVm.getLoginStatus()
    val uiState = loginVm.uiState.collectAsState().value

    val showConfirm = remember {
        mutableStateOf(false)
    }

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

    ConfirmDialog(title = "Apakah kamu yakin pingin keluar ?" , icon = R.drawable.logout , boolean = showConfirm , cancel = {
        showConfirm.value = false
    }) {
        loginVm.addToken("")
        loginVm.deleteLoginStatus()
        showConfirm.value = false
        navController.navigate(Routes.MainNavigation.route+"/"+Routes.Onboarding.route) {
            popUpTo(0) {
                inclusive = true

            }

        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.background
    ) {
       if (uiState.isNotEmpty()) {
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
                               text = uiState.first().name,
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
                               .wrapContentWidth(End)
                       ) {
                           EditButton(viewModel.edit.value) {
                               showConfirm.value = true
                           }
                       }

                       if (uiState.first().no_bpjs.isNotEmpty()) {
                           Spacer(modifier = Modifier.height(20.dp))
                           viewModel.nik.value = uiState.first().no_bpjs
                           ProfilContent(
                               R.drawable.nik_icon, viewModel.nik, viewModel.edit.value,
                               KeyboardType.NumberPassword
                           )
                       }

                       Spacer(modifier = Modifier.height(20.dp))
                       viewModel.name.value = uiState.first().name
                       ProfilContent(
                           R.drawable.name_icon, viewModel.name, viewModel.edit.value,
                           KeyboardType.Text
                       )

                       Spacer(modifier = Modifier.height(20.dp))
                       viewModel.date.value = uiState.first().tanggal_lahir
                       DatePicker(
                           context = current,
                           date = viewModel.date,
                           color = dateBorderColor,
                           colorSecond = dateColor,
                           viewModel.edit.value
                       )

                       Spacer(modifier = Modifier.height(20.dp))
                       viewModel.address.value = uiState.first().alamat
                       ProfilContent(
                           R.drawable.location_icon, viewModel.address, viewModel.edit.value,
                           KeyboardType.Text
                       )

                       Spacer(modifier = Modifier.height(20.dp))
                       viewModel.phone.value = uiState.first().telepon
                       ProfilContent(
                           R.drawable.phone_icon, viewModel.phone, viewModel.edit.value,
                           KeyboardType.Phone
                       )
                       Spacer(modifier = Modifier.height(20.dp))
                       ButtonClick(color = MaterialTheme.colors.primary, text = "Ubah", modifier = Modifier.fillMaxWidth() ) {
                           navController.navigate(Routes.FiturRoute.route+"/"+Routes.DetailProfile.route)
                       }
                       Spacer(modifier = Modifier.height(20.dp))
                   }
               }
           }
       } else {
            ProfileShimering()
       }
    }
}







