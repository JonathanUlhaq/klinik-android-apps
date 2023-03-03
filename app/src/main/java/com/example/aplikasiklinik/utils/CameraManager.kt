package com.example.aplikasiklinik.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.example.aplikasiklinik.BuildConfig
import kotlinx.coroutines.launch
import java.io.File
import java.util.Objects

fun Context.createImageFile(): File {
    val imageFile = "profile"
    return File.createTempFile(
        imageFile,
        ".png",
        externalCacheDir
    )
}


@Composable
fun LaunchCamera(captureImage:MutableState<Uri>) {
    val context = LocalContext.current
    val file = context.createImageFile()
    val uri = FileProvider.getUriForFile(
        Objects.requireNonNull(context),
        BuildConfig.APPLICATION_ID+".provider",
        file
    )

    val coroutine = rememberCoroutineScope()

    val cameraLaunch = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture(), onResult = {
        captureImage.value = uri
    } )


    val permission = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission() , onResult = {
        if (it) {
            Toast.makeText(context, "Akses kamera diizinkan", Toast.LENGTH_SHORT).show()
            coroutine.launch {
                cameraLaunch.launch(uri)
            }
        } else {
            Toast.makeText(context, "Akses kamera tidak diizinkan", Toast.LENGTH_SHORT).show()
        }
    })

    val permissionCheckResult = ContextCompat.checkSelfPermission(context,Manifest.permission.CAMERA)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED){
        Toast.makeText(context, "Akses kamera diizinkan", Toast.LENGTH_SHORT).show()
        SideEffect {
            cameraLaunch.launch(uri)
        }
    } else {
        Toast.makeText(context, "Akses kamera tidak diizinkan", Toast.LENGTH_SHORT).show()
        SideEffect {
            permission.launch(Manifest.permission.CAMERA)
        }

    }

}




