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
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.utils.ConstUrl
import com.example.aplikasiklinik.utils.LaunchCamera
import com.example.aplikasiklinik.view.login.LoginViewModel
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.register.RegisForm
import com.example.aplikasiklinik.view.register.RegisterViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.destination
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel,
    mainVm: MainActivityViewModel,
    loginVm:LoginViewModel,
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

    val nik = remember {
        mutableStateOf("")
    }
    val name = remember {
        mutableStateOf("")
    }
    val address = remember {
        mutableStateOf("")
    }
    val phone = remember {
        mutableStateOf("")
    }
    val date = remember {
        mutableStateOf("")
    }
    val hide = remember {
        mutableStateOf(false)
    }

    val isLoading = remember {
        mutableStateOf(false)
    }

    val detectFirst = remember {
        mutableStateOf(false)
    }

    val isError = remember {
        mutableStateOf(false)
    }

    val capturedImagebyUri = remember {
        mutableStateOf(Uri.EMPTY)
    }

    val stillBlank = remember {
        mutableStateOf(false)
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

    LoadingScreen(boolean = isLoading.value)
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
                        AnimatedVisibility(visible = !hide.value) {
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
                                            nik,
                                            hide,
                                            name,
                                            context,
                                            date,
                                            address,
                                            phone,
                                            isError,
                                            detectFirst,
                                            stillBlank
                                        ) {
                                            login.invoke(true)
                                            navController.navigate(Routes.Login.route)
                                        }
                                        Spacer(modifier = Modifier.height(8.dp))
                                        VerificationButton {
                                            login.invoke(false)
                                            if (
                                                !detectFirst.value &&
                                                        name.value.isNotEmpty() &&
                                                        !capturedImagebyUri.value.path.isNullOrEmpty() &&
                                                        date.value.isNotEmpty() &&
                                                        address.value.isNotEmpty()
                                            ) {
                                                runBlocking {
                                                    val file = File(capturedImagebyUri.value.path!!)
                                                    val compresspr = Compressor.compress(context, file) {
                                                        default()
                                                        destination(file)
                                                    }
                                                    val requestBody = compresspr.asRequestBody("image/*".toMediaType())
                                                    val gambar = MultipartBody.Part.createFormData(
                                                        "foto",
                                                        compresspr.name,
                                                        requestBody
                                                    )


                                                    val tanggalLahir =
                                                        RequestBody.create("text/plain".toMediaType(), date.value)
                                                    val alamatRequest = RequestBody.create(
                                                        "text/plain".toMediaType(),
                                                        address.value
                                                    )
                                                    val noBpjs =
                                                        RequestBody.create("text/plain".toMediaType(), nik.value)
                                                    val nameRequest =
                                                        RequestBody.create("text/plain".toMediaType(), name.value)
                                                    val phoneRequest =
                                                        RequestBody.create("text/plain".toMediaType(), phone.value)
                                                    val passwordRequest =
                                                        RequestBody.create("text/plain".toMediaType(), "12345678")

                                                    viewModel.registerAkun(
                                                        foto = gambar,
                                                        nama = nameRequest,
                                                        telepon = phoneRequest,
                                                        alamat = alamatRequest,
                                                        tanggal_lahir = tanggalLahir,
                                                        isError = isError,
                                                        password = passwordRequest,
                                                        no_bpjs = noBpjs,
                                                        isLoading = isLoading
                                                    ){ response ->
                                                        viewModel.pref.saveAlamat(address.value)
                                                        viewModel.pref.saveFoto(ConstUrl.BASE_URL + response.user?.foto!!)
                                                        viewModel.pref.saveBPJS(nik.value)
                                                        viewModel.pref.saveLahir(date.value)
                                                        viewModel.pref.saveNama(name.value)
                                                        viewModel.pref.saveTelepon(phone.value)
                                                        loginVm.getOtp(otp =phone.value){}
                                                        navController.navigate(Routes.Otp.route)
                                                    }
                                                }


                                            } else {
                                                stillBlank.value = true
                                            }
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





