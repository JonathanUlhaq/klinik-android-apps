package com.example.aplikasiklinik.view.profil

import android.app.DownloadManager
import android.content.Context
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.aplikasiklinik.R
import com.example.aplikasiklinik.components.*
import com.example.aplikasiklinik.utils.ConstUrl
import com.example.aplikasiklinik.utils.UriToFile
import com.example.aplikasiklinik.view.navigation.Routes
import com.example.aplikasiklinik.widget.profil.ProfilContent
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.default
import id.zelory.compressor.constraint.destination
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.concurrent.Executors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailProfile(navController: NavController, vm: ProfilViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(
        color = MaterialTheme.colors.primary
    )
    val scrollState = rememberScrollState()
    val nik = remember {
        mutableStateOf(vm.sharePref().getBpjs()!!)
    }
    val nikValidator = remember {
        mutableStateOf(false)
    }
    nikValidator.value = nik.value.length < 16
    val name = remember {
        mutableStateOf(vm.sharePref().getNama()!!)
    }
    val address = remember {
        mutableStateOf(vm.sharePref().getAlamat()!!)
    }
    val pasienSelected = remember {
        mutableStateOf(if(vm.sharePref().getBpjs().isNullOrEmpty()) false else true)
    }
    val listJenisPasien = listOf(
        "Pasien Non BPJS",
        "Pasien BPJS"
    )
    val currentSelected = remember {
        mutableStateOf(
            if (vm.sharePref().getBpjs().isNullOrEmpty()) {
                0
            } else {
                1
            }
        )
    }
    val phone = remember {
        mutableStateOf(vm.sharePref().getTelepon()!!)
    }
    val date = remember {
        mutableStateOf(vm.sharePref().getLahir()!!)
    }
    val edit = remember {
        mutableStateOf(false)
    }
    val isLoading = remember {
        mutableStateOf(false)
    }
    val isError = remember {
        mutableStateOf(false)
    }
    val context = LocalContext.current


//        val request = DownloadManager.Request(Uri.parse(vm.sharePref().getFoto()))
//            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"profile.png")
//            .setAllowedOverMetered(true)
//
//        val download = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager
//        download.enqueue(request)

    downloadImage(context,vm.sharePref().getFoto()!!)

    val cacheDir = context.cacheDir // Get the cache directory
    val fileName = "profile.png" // Name of the file to be saved

    val fileLocation = File(cacheDir, fileName)

    val capturedImagebyUri = remember {
        mutableStateOf(Uri.EMPTY)
    }

    val output: File = getOutputDirectory(context)

//    val photoFile = File(
//        capturedImagebyUri.value.path!!
//    )

//    val fileUpload = File(capturedImagebyUri.value.path!!)

    val showPermission = remember {
        mutableStateOf(false)
    }
    val detectFirst = remember {
        mutableStateOf(false)
    }
    val firstString = remember {
        mutableStateOf("")
    }
    if (phone.value.isNotEmpty()) {
        firstString.value = phone.value.first().toString()
        detectFirst.value = firstString.value == "0"
    }
    val coroutine = rememberCoroutineScope()
    val changeContent = remember {
        mutableStateOf(false)
    }
    val openCamera = remember {
        mutableStateOf(false)
    }
    if (showPermission.value) {
        requestCameraPermission(context = context, openCamera = openCamera, changeContent)
    }

    LoadingScreen(boolean = isLoading.value)

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
                                                Image(
                                                    painter = rememberImagePainter(
                                                        vm.sharePref().getFoto()
                                                    ),
                                                    contentDescription = null,
                                                    modifier = Modifier
                                                        .size(110.dp),
                                                    contentScale = ContentScale.Crop
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
                        Text(text = "Jenis Pasien",
                            style = MaterialTheme.typography.h1,
                            color = Color.White,
                            fontSize = 12.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Surface(
                            color = Color.Transparent,
                            border = BorderStroke(2.dp, Color.White),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .height(38.dp)
                                .width(210.dp)
                        ) {
                            Row(
                                Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {

                                    val colorFont by animateColorAsState(targetValue = if (pasienSelected.value) Color.White else Color.White.copy(0.5f))
                                    val colorSurface by animateColorAsState(targetValue = if (pasienSelected.value) MaterialTheme.colors.primary else MaterialTheme.colors.primary.copy(0.5f))
                                    Surface(
                                        color = MaterialTheme.colors.primary,
                                        shape = RoundedCornerShape(12.dp),
                                        onClick = {
                                            if (currentSelected.value == 1) {
                                                nik.value = ""
                                                currentSelected.value = 0
                                                pasienSelected.value = false
                                            } else {
                                                currentSelected.value = 1
                                                pasienSelected.value = true
                                            }
                                        },
                                        modifier = Modifier
                                            .fillMaxHeight()
                                    ) {
                                        Text(if (currentSelected.value == 1) "Ganti pasien non BPJS" else "Ganti Pasien BPJS",
                                            fontSize = 12.sp,
                                            color = Color.White,
                                            style = MaterialTheme.typography.h1,
                                            modifier = Modifier
                                                .padding(12.dp))
                                    }


                            }
                        }
                        if (pasienSelected.value) {
                            Spacer(modifier = Modifier.height(20.dp))
                            ProfilContent(
                                R.drawable.nik_icon, nik, true,
                                KeyboardType.Number,
                                isNik = true
                            )
                            if (nikValidator.value) {
                                Text(text = "Masukkan Nomor BPJS 13 Digit",
                                    color = MaterialTheme.colors.error,
                                    fontSize = 12.sp,
                                    style = MaterialTheme.typography.caption)
                            }
                        }
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
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            DisableInputOutlined()
                            Spacer(modifier = Modifier.width(8.dp))
                            ProfilContent(
                                R.drawable.phone_icon, phone, true,
                                KeyboardType.Phone
                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        AnimatedVisibility(visible = detectFirst.value) {
                            Text(
                                "* Isiikan nomor telepon setelah angka 0",
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.error,
                                style = MaterialTheme.typography.caption
                            )
                        }
                        Spacer(modifier = Modifier.height(7.dp))
                        AnimatedVisibility(visible = isError.value) {
                            Text(
                                text = "Mohon lengkapi data diri anda",
                                color = MaterialTheme.colors.error,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        AnimatedVisibility(
                            visible =
                            !vm.sharePref().getNama()!!.matches(Regex(name.value)) ||
                                    !vm.sharePref().getNumber()!!.matches(Regex(phone.value)) ||
                                    !vm.sharePref().getBpjs()!!.matches(Regex(nik.value)) ||
                                    !vm.sharePref().getLahir()!!.matches(Regex(date.value)) ||
                                    !vm.sharePref().getAlamat()!!.matches(Regex(address.value)) ||
                                    changeContent.value
                        ) {
                            ButtonClick(
                                color = MaterialTheme.colors.primary, text = "Simpan Perubahan",
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                if (
                                    name.value.isNotEmpty() &&
                                    phone.value.isNotEmpty() &&
                                    address.value.isNotEmpty() &&
                                    date.value.isNotEmpty()
                                ) {
                                    if (fileLocation.exists() && capturedImagebyUri.value.path.isNullOrEmpty()) {
                                        Log.d("WEH EKSIS","IYA NII")
                                        try {
                                            capturedImagebyUri.value = Uri.fromFile(fileLocation)
                                        } catch (e:Exception) {
                                            Log.e("ERROR",e.toString())
                                        }
                                    } else {
                                        Log.d("WEH GAK EKSIS","IYA NII")
                                    }

                                        runBlocking {
                                            val file = File(capturedImagebyUri.value.path!!)
                                            val compresspr = Compressor.compress(context, file) {
                                                default()
                                                destination(file)
                                            }


                                            val nameRequest =
                                                RequestBody.create("text/plain".toMediaType(), name.value)
                                            val phoneRequest =
                                                RequestBody.create("text/plain".toMediaType(), phone.value)
                                            val alamatRequest = RequestBody.create(
                                                "text/plain".toMediaType(),
                                                address.value
                                            )
                                            val noBpjs =
                                                RequestBody.create("text/plain".toMediaType(), nik.value)
                                            val tanggalLahir =
                                                RequestBody.create("text/plain".toMediaType(), date.value)

                                            val requestBody = compresspr.asRequestBody("image/*".toMediaType())
                                            val gambar = MultipartBody.Part.createFormData(
                                                "foto",
                                                compresspr.name,
                                                requestBody
                                            )
                                            vm.updateProfile(
                                                id = vm.getId()!!.toInt(),
                                                alamat = alamatRequest,
                                                foto = gambar,
                                                no_bpjs = noBpjs,
                                                tanggal_lahir = tanggalLahir,
                                                name = nameRequest,
                                                telepon = phoneRequest,
                                                password = nameRequest,
                                                isLoading = isLoading,
                                                eventError = {
                                                    Toast.makeText(
                                                        context,
                                                        "Coba lagi, server sedang sibuk",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            ) {
                                                vm.pref.saveAlamat(address.value)
                                                vm.pref.saveFoto(ConstUrl.BASE_URL + it.data.foto)
                                                vm.pref.saveBPJS(nik.value)
                                                vm.pref.saveLahir(date.value)
                                                vm.pref.saveNama(name.value)
                                                vm.pref.saveTelepon(phone.value)
                                                navController.navigate(Routes.Home.route + "/" + Routes.Profile.route) {
                                                    popUpTo(0)
                                                }
                                                Toast.makeText(
                                                    context,
                                                    "Profile berhasil diperbarui",
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }

                                } else {
                                    isError.value = true
                                }


                            }
                        }

                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

            }
        }
    }
}
fun downloadImage(context: Context, imageUrl: String) {
    val cacheDir = context.cacheDir // Get the cache directory
    val fileName = "profile.png" // Name of the file to be saved


    GlobalScope.launch(Dispatchers.IO) {
       try {
           val file = File(cacheDir, fileName)
           val url = URL(imageUrl)
           val connection = url.openConnection()
           connection.connect()

           val inputStream: InputStream = connection.getInputStream()
           val outputStream = FileOutputStream(file)

           val buffer = ByteArray(1024)
           var len: Int
           while (inputStream.read(buffer).also { len = it } > 0) {
               outputStream.write(buffer, 0, len)
           }
           outputStream.close()
           inputStream.close()
           if (file.exists()) {
               Log.d("EXISY",file.path)
           } else {
               Log.d("GAJKKK","ile.path")
           }
       } catch (e:Exception) {
           Log.d("WRONG",e.toString())
       }
    }
//    fromFile(fileLocation)

}

