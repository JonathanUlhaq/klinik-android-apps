package com.example.aplikasiklinik

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.IBinder
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.*
import com.example.aplikasiklinik.components.CameraView
import com.example.aplikasiklinik.model.ThemeModeModel
import com.example.aplikasiklinik.ui.theme.AplikasiKlinikTheme
import com.example.aplikasiklinik.utils.*
import com.example.aplikasiklinik.view.currentantrian.CurrenAntrViewModel
import com.example.aplikasiklinik.view.main.MainScreen
import com.example.aplikasiklinik.view.mainactivity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Suppress("DEPRECATION")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var output: File
    private lateinit var cameraExecutor: ExecutorService
    private lateinit var foreground:ForegroundService
    private var isTimerServiceBound: Boolean = false

    private var shouldShowCamera = mutableStateOf(false)
    private val permission = mutableStateOf(false)
    private var shouldShowPhoto = mutableStateOf(false)
    lateinit var notif: NotificationManager
    var photoUri: Uri = Uri.EMPTY
    private val timerServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            foreground = (p1 as ForegroundService.TimerBinder).service
            isTimerServiceBound = true
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            isTimerServiceBound = false
        }
    }

    @Inject
    lateinit var tokenManager: TokenManager


    private val requestPermissionLauncer = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { disetujui ->
        if (disetujui) {
            shouldShowCamera.value = true

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        val intente = Intent(this, ForegroundService::class.java)
        this.startService(intente)
        bindService(
            intente,
            timerServiceConnection,
            Context.BIND_AUTO_CREATE
        )
        setContent {
            val viewModel = hiltViewModel<MainActivityViewModel>()
            val state = viewModel.uiState.collectAsState().value
            var boolean = viewModel.boolean.value
            val antrian = hiltViewModel<CurrenAntrViewModel>()

            antrian.getCurrentAntri()
            val antriState = antrian.uiState.collectAsState().value


            boolean = isSystemInDarkTheme()

//            val constraint = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//            val worker = PeriodicWorkRequestBuilder<NotificationWorker>(
//                repeatInterval = 5, TimeUnit.SECONDS
//            )
//                .setConstraints(constraint)
//                .build()
//
//            WorkManager.getInstance(this)
//                .enqueue(worker)


//            val constraint = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//            val notificationManager = PeriodicWorkRequestBuilder<NotificationWorker>(
//                repeatInterval = 15,TimeUnit.MINUTES
//            )
//                .setConstraints(constraint)
//                .build()
//            WorkManager.getInstance(this).enqueue(notificationManager)




//            if (antriState.kurang_antrian != null) {
//
//                Log.e("KEBACA ",antriState.kurang_antrian.toString())
//               if (antriState.kurang_antrian < 3) {
////                   notif.notificationManager()
//
//                }
//            }

            AplikasiKlinikTheme(
                darkTheme = if (state.isEmpty()) boolean else state.first().darkMode
            ) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box {
                        MainScreen(if (state.isEmpty()) boolean else state.first().darkMode,
                            antrian = 1,
                            openCam = shouldShowCamera.value,
                            cameraClick = {
                                requestCameraPermission()
                            },
                            uri = photoUri,
                            contentCamera = {
                                if (shouldShowCamera.value) {
                                    CameraView(
                                        outputDirectory = output,
                                        executor = cameraExecutor,
                                        onImageCapture = ::handleImageCapture,
                                        onError = { Log.e("GG", "ERROR") },
                                        closeCamera = {
                                            shouldShowCamera.value = false
                                            cameraExecutor.shutdown()
                                        }
                                    )
                                }
                            }) {
//                            viewModel.insertBoolean(if (state.isEmpty()) ThemeModeModel(0,
//                                !boolean ) else ThemeModeModel(0, !state.first().darkMode))
                        }
                    }
                }
            }
            output = getOutputDirectory()
            Log.d("GET OUTPUTTT", output.toString())
            cameraExecutor = Executors.newSingleThreadExecutor()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content)) { view, insets ->
            val bottom = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
            view.updatePadding(bottom = bottom)
            insets
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        val intente = Intent(this, ForegroundService::class.java)
//        this.startService(intente)
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("Gran", "CAMERA GRANTED")
                shouldShowCamera.value = true
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("Gran", "Show Camera Dialog")
            else -> requestPermissionLauncer.launch(Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        shouldShowCamera.value = false
        photoUri = uri
        cameraExecutor.shutdown()
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

}
