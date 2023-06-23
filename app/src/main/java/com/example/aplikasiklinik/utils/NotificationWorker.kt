package com.example.aplikasiklinik.utils

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters

import com.example.aplikasiklinik.network.APIEndpoint
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted  appContext: Context,
    @Assisted workerParams: WorkerParameters,
     private val api:APIEndpoint
) : Worker(appContext,workerParams) {

    lateinit var result:ListenableWorker.Result
    @Inject
    lateinit var context:Context

    override  fun doWork(): Result  {
        Log.d("SUSUSUSU","SILLLL:L")
        runBlocking {
            try {
                val data = api.getCurrentAntrian()
                Log.d("SAAASAAS","SILLLL:L")
                if (data.kurang_antrian != null && data.kurang_antrian < 3) {
                    val notification = NotificationManager(context,"Persiapkan dirimu, antrian kurang ${data.kurang_antrian} orang")
                    notification.notificationManager()
                    Log.d("BERHASILLL","SILLLL:L")
                }

                result = Result.success()
            } catch (e:Exception) {
                Log.d("BERHASILLL",e.toString())
                result = Result.failure()

            }
        }
        return result
    }
}