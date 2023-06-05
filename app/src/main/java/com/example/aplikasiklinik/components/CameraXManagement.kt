package com.example.aplikasiklinik.components

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.example.aplikasiklinik.R
import kotlinx.coroutines.launch

@Composable
fun CameraView(
    outputDirectory: File,
    executor: Executor,
    closeCamera:() -> Unit,
    onImageCapture: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val cameraFace = CameraSelector.LENS_FACING_FRONT
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val preview = Preview.Builder().build()
    val previewView = remember {
        PreviewView(context)
    }
    val imageCapture: ImageCapture = remember {
        ImageCapture.Builder().build()
    }

    val cameraSelector = CameraSelector.Builder()
        .requireLensFacing(cameraFace)
        .build()

    LaunchedEffect(cameraFace) {
        val cameraProvider = context.getCameraProvider()
        cameraProvider.unbindAll()
        cameraProvider.bindToLifecycle(
            lifecycleOwner,
            cameraSelector,
            preview,
            imageCapture
        )
        preview.setSurfaceProvider(previewView.surfaceProvider)
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.onPrimary)) {
        Box(modifier = Modifier
            .padding(16.dp)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Start)) {
                    IconButton(onClick = { closeCamera.invoke() }) {
                        Icon(painter = painterResource(id = R.drawable.back_arrow),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp))
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                AndroidView(factory = {previewView},
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(280.dp))
                Spacer(modifier = Modifier.height(6.dp))
                Text("Foto Bagian Wajah",
                    style = MaterialTheme.typography.h1,
                    color = Color.White,
                    fontSize = 16.sp)
                Spacer(modifier = Modifier.weight(1f))
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(CenterHorizontally)) {
                    IconButton(onClick = {
                        takePhoto(
                            "yyyy-MM-dd-HH-mm-ss-SSS",
                            imageCapture = imageCapture,
                            outputDirectory,
                            executor,
                            onImageCapture,
                            onError
                        )
                    }) {
                        Icon(painter = painterResource(id = R.drawable.click_photo),
                            contentDescription = null)
                    }
                }
            }
        }
    }

}

private fun takePhoto(
    fileName: String,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCapture: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
) {
    val photoFile = File(
        outputDirectory,
        SimpleDateFormat(fileName, Locale.TAIWAN).format(System.currentTimeMillis()) + ".png"
    )

    val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

    imageCapture.takePicture(
        outputOptions,
        executor,
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                onError(exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                Log.e("SIMPAN FOTO",savedUri.toString())
                onImageCapture(savedUri)
            }
        }
    )
}

private suspend fun Context.getCameraProvider(): ProcessCameraProvider =
    suspendCoroutine { continuation ->
        ProcessCameraProvider.getInstance(this).also { cameraProvider ->
            cameraProvider.addListener({
                continuation.resume(cameraProvider.get())
            }, ContextCompat.getMainExecutor(this))
        }
    }


@Composable
fun requestCameraPermission(
    context: Context,
    openCamera: MutableState<Boolean>
) {
    val coroutine = rememberCoroutineScope()
    val permission = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = {
            if (it) {
                Toast.makeText(context, "Akses kamera diizinkan", Toast.LENGTH_SHORT).show()
                coroutine.launch {
                    openCamera.value = true
                }
            } else {
                Toast.makeText(context, "Akses kamera tidak diizinkan", Toast.LENGTH_SHORT).show()
            }
        })

    val permissionCheckResult =
        ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(context, "Akses kamera diizinkan", Toast.LENGTH_SHORT).show()
        SideEffect {
            openCamera.value = true
        }
    } else {
        Toast.makeText(context, "Akses kamera tidak diizinkan", Toast.LENGTH_SHORT).show()
        SideEffect {
            permission.launch(Manifest.permission.CAMERA)
        }

    }
}

fun getOutputDirectory(context: Context):File {
    val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
        File(it,context.resources.getString(R.string.app_name)).apply { mkdirs() }
    }
    return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
}

