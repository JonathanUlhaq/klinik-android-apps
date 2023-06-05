package com.example.aplikasiklinik.view.register

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.CameraView
import com.example.aplikasiklinik.components.VerificationButton
import com.example.aplikasiklinik.components.getOutputDirectory
import com.example.aplikasiklinik.components.requestCameraPermission
import com.example.aplikasiklinik.utils.LaunchCamera
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.register.RegisForm
import com.example.aplikasiklinik.widget.register.RegisterViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    mainVm: MainActivityViewModel,
    dark: Boolean,
    cameraClick:() -> Unit,
    uri:Uri,
    openCam:Boolean,
    contentCamera:@Composable () -> Unit,
    login: (Boolean) -> Unit
) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.background
    )
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
    val scrollable = rememberScrollState()

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
            backgroundColor = MaterialTheme.colors.background
        ) {
            Surface(
                color = Color.Transparent,
                modifier = Modifier
                    .padding(it)
            ) {

                Column {
                    if (openCam) {
                        contentCamera.invoke()
                    } else {
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
                                        painter = painterResource(id = if (dark) R.drawable.dark_register_image else R.drawable.register_image),
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
                        Spacer(modifier = Modifier.height(32.dp))

                        Box {
                            Surface(
                                color = MaterialTheme.colors.primary,
                                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 50.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .offset(y = 42.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                        .offset(y = 60.dp)
                                ) {
                                    Spacer(modifier = Modifier.height(14.dp))
                                    Column(
                                        modifier = Modifier
                                            .verticalScroll(state = scrollable, enabled = true)
                                            .fillMaxHeight()
                                            .wrapContentHeight(CenterVertically),
                                    ) {
                                        RegisForm(
                                            viewModel.nik,
                                            viewModel.hide,
                                            viewModel.name,
                                            context,
                                            viewModel.date,
                                            viewModel.address,
                                            viewModel.phone
                                        ) {
                                            login.invoke(true)
                                            navController.navigate(Routes.Login.route)
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        VerificationButton {
                                            login.invoke(false)
                                            navController.navigate(Routes.Otp.route)
                                        }

                                        Spacer(
                                            modifier = Modifier
                                                .height(70.dp)
                                                .imePadding()
                                        )

                                    }
                                }
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentSize(Alignment.Center)
                                    .offset(y = (-32).dp)
                            ) {
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
                                                    horizontalAlignment = CenterHorizontally
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
                    }

                }

            }
        }
    }


}





