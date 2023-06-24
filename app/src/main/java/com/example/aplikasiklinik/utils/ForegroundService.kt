package com.example.aplikasiklinik.utils

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.work.*
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.network.APIEndpoint
import com.example.aplikasiklinik.view.currentantrian.CurrenAntrViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class ForegroundService:Service() {

    @Inject
    lateinit var api:APIEndpoint
    private lateinit var timer: Timer

    private val binder: Binder = TimerBinder()

    override fun onBind(p0: Intent?): IBinder = binder

    inner class TimerBinder : Binder() {
        val service: ForegroundService
            get() = this@ForegroundService
    }

    override fun onCreate() {
        super.onCreate()
        timer = Timer()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val context = this

        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val worker = PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = 60,TimeUnit.SECONDS
        )
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this)
            .enqueue(worker)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        val constraint = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val worker = PeriodicWorkRequestBuilder<NotificationWorker>(
            repeatInterval = 60,TimeUnit.SECONDS
        )
            .setConstraints(constraint)
            .build()

        WorkManager.getInstance(this)
            .enqueue(worker)
    }

}