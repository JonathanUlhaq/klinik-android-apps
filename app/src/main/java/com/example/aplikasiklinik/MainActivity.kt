package com.example.aplikasiklinik

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.aplikasiklinik.components.CameraView
import com.example.aplikasiklinik.model.ThemeModeModel
import com.example.aplikasiklinik.ui.theme.AplikasiKlinikTheme
import com.example.aplikasiklinik.view.main.MainScreen
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var output: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera = mutableStateOf(false)
    private val permission = mutableStateOf(false)
    private var shouldShowPhoto = mutableStateOf(false)

    var photoUri: Uri = Uri.EMPTY


    private val requestPermissionLauncer = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
            disetujui ->
        if (disetujui) {
            shouldShowCamera.value = true

        } else {

        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            val viewModel by viewModels<MainActivityViewModel>()
            val state = viewModel.uiState.collectAsState().value
            var boolean = viewModel.boolean.value
            boolean = isSystemInDarkTheme()
            AplikasiKlinikTheme(
                darkTheme = if (state.isEmpty()) boolean else state.first().darkMode
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box {
                        MainScreen(if (state.isEmpty()) boolean else state.first().darkMode, antrian = 1, openCam = shouldShowCamera.value ,cameraClick = {
                            requestCameraPermission()
                            Log.d("ANJAYY","HJAHGHAHH")
                        }, uri = photoUri,
                            contentCamera = {
                                if (shouldShowCamera.value) {
                                    CameraView(
                                        outputDirectory =  output,
                                        executor = cameraExecutor ,
                                        onImageCapture = ::handleImageCapture ,
                                        onError = {Log.e("GG","ERROR")}
                                    )
                                }
                            }) {
                            viewModel.insertBoolean(if (state.isEmpty()) ThemeModeModel(0,
                                !boolean ) else ThemeModeModel(0, !state.first().darkMode))
                        }
                    }
                }
            }
            output = getOutputDirectory()
            cameraExecutor = Executors.newSingleThreadExecutor()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Gran","CAMERA GRANTED")
                shouldShowCamera.value = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("Gran","Show Camera Dialog")
            else -> requestPermissionLauncer.launch(Manifest.permission.CAMERA)
        }
    }
    private fun handleImageCapture(uri:Uri) {
        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory():File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it,resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }
}
