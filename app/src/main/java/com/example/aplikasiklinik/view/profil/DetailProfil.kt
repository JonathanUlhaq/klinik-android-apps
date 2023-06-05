package com.example.aplikasiklinik.view.profil

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.utils.LaunchCamera
import com.example.aplikasiklinik.utils.createImageFile
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.profil.ProfilContent
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import okhttp3.Route
import java.io.File
import java.util.concurrent.Executors

@Composable
fun DetailProfile(navController: NavController) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.primary
    )
    val scrollState = rememberScrollState()
    val nik = remember {
        mutableStateOf("01293123982")
    }
    val name = remember {
        mutableStateOf("Ragnar Holbrook")
    }
    val address = remember {
        mutableStateOf("Magetan")
    }
    val phone = remember {
        mutableStateOf("08129292")
    }
    val date = remember {
        mutableStateOf("03/03/2023")
    }
    val edit = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current
    val capturedImagebyUri = remember {
        mutableStateOf(Uri.EMPTY)
    }
    val showPermission = remember {
        mutableStateOf(false)
    }

    val openCamera = remember {
        mutableStateOf(false)
    }
    if (showPermission.value) {
        requestCameraPermission(context = context, openCamera = openCamera)
    }

    val output: File = getOutputDirectory(context)
    val cameraExecutor = Executors.newSingleThreadExecutor()
    if (openCamera.value) {
        CameraView(outputDirectory = output, executor = cameraExecutor,
            onImageCapture = {
                capturedImagebyUri.value = it
                Log.d("ALAMAT FILE", it.toString())
                showPermission.value = false
                openCamera.value = false
                cameraExecutor.shutdown()
            }, onError = {
                Log.e("ERRORR", it.toString())
            }, closeCamera = {
                showPermission.value = false
                openCamera.value = false
                cameraExecutor.shutdown()
            })
    } else {
        Scaffold(
            backgroundColor = MaterialTheme.colors.background,
            topBar = {
                CustomTopBar(
                    navController,
                    "Ubah Profile",
                    Routes.Home.route + "/" + Routes.Profile.route
                )
            }
        ) {
            Surface(modifier = Modifier.padding(it), color = MaterialTheme.colors.background) {

                Column {
                    Box(
                        modifier = Modifier
                            .height(150.dp)
                            .background(Color.Transparent)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(bottomStart = 40.dp, bottomEnd = 40.dp))
                                .height(100.dp)
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.onPrimary)
                        )
                        Column(
                            modifier = Modifier

                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.CenterHorizontally),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(10.dp))

                            IconButton(onClick = {
                                showPermission.value = true
                                if (capturedImagebyUri.value.path?.isNotEmpty() == true) {
                                    capturedImagebyUri.value = Uri.EMPTY
                                }
                            }) {
                                Surface(
                                    color = MaterialTheme.colors.background,
                                    border = BorderStroke(5.dp, MaterialTheme.colors.primary),
                                    shape = CircleShape
                                ) {
                                    Surface(
                                        color = MaterialTheme.colors.onBackground,
                                        shape = CircleShape,
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .size(110.dp)
                                    ) {
                                        if (capturedImagebyUri.value.path?.isNotEmpty() == true) {
                                            showPermission.value = false
                                            Image(
                                                painter = rememberImagePainter(capturedImagebyUri.value),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(110.dp),
                                                contentScale = ContentScale.Crop
                                            )
                                        } else {
                                            Column(
                                                Modifier
                                                    .fillMaxWidth()
                                                    .fillMaxHeight(),
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    text = "Unggah \n Foto",
                                                    style = MaterialTheme.typography.body2,
                                                    color = MaterialTheme.colors.surface.copy(0.35F),
                                                    textAlign = TextAlign.Center
                                                )
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier =
                        Modifier
                            .padding(14.dp)
                            .verticalScroll(scrollState)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        ProfilContent(
                            R.drawable.nik_icon, nik, true,
                            KeyboardType.NumberPassword
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        ProfilContent(
                            R.drawable.name_icon, name, true,
                            KeyboardType.Text
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        DatePicker(
                            context = context,
                            date = date,
                            color = MaterialTheme.colors.primaryVariant,
                            colorSecond = MaterialTheme.colors.surface,
                            true
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        ProfilContent(
                            R.drawable.location_icon, address, true,
                            KeyboardType.Text
                        )

                        Spacer(modifier = Modifier.height(20.dp))
                        ProfilContent(
                            R.drawable.phone_icon, phone, true,
                            KeyboardType.Phone
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        ButtonClick(
                            color = MaterialTheme.colors.primary, text = "Simpan Perubahan",
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {

                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

            }
        }
    }
}
