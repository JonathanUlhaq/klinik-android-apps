package com.example.aplikasiklinik.utils

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.view.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BackgroundService : Service() {


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        val notif = NotificationManager(this, "Segera cek antrian kamu ")
        runBlocking {
            notif.notificationManager()
        }
    }
}



