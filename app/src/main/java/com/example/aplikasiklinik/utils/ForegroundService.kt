package com.example.aplikasiklinik.utils

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.work.*
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.model.notification.NotificationBody
import com.example.aplikasiklinik.model.notification.PushNotification
import com.example.aplikasiklinik.network.APIEndpoint
import com.example.aplikasiklinik.network.NotifEndpoint
import com.example.aplikasiklinik.view.currentantrian.CurrenAntrViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class ForegroundService:Service() {



    private lateinit var timer: Timer
    lateinit var retrofit:Retrofit
    lateinit var api:NotifEndpoint
    lateinit var client:OkHttpClient
    private val binder: Binder = TimerBinder()

    override fun onBind(p0: Intent?): IBinder = binder

    inner class TimerBinder : Binder() {
        val service: ForegroundService
            get() = this@ForegroundService
    }

    override fun onCreate() {
        super.onCreate()
//        timer = Timer()
//        val logging = HttpLoggingInterceptor()
//        logging.level = HttpLoggingInterceptor.Level.BODY
//        client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
//        retrofit = Retrofit.Builder()
//            .baseUrl("https://fcm.googleapis.com/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//        api = retrofit.create(NotifEndpoint::class.java)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val context = this

//        runBlocking {
//            Log.d("TERJADI LAHHHH","LAH TERJADI")
//            api.sendNotification(
//                PushNotification(
//                    to = "fS08IsmmQJyVtA4tM1Wg2F:APA91bF1LWH1sfIW4JKcgKJXWScQElgw73E8ImTQhbZLDWTRpU29jInRKNLDionDzYc6T9e7GQcH4hoHGx9kWgLC-ouI06HRtYi-EOYcQIDLTjyLUhhD9zPCG_aLJ0zRwZXHa7YLf3Cl",
//                    notification = NotificationBody(
//                        title = "Antrii",
//                        body = "asdasdsad",
//                        mutable_content = 1,
//                        sound = "Tri-tone"
//                    )
//                )
//            )
//        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
//        runBlocking {
//            api.sendNotification(
//                PushNotification(
//                    to = "fS08IsmmQJyVtA4tM1Wg2F:APA91bF1LWH1sfIW4JKcgKJXWScQElgw73E8ImTQhbZLDWTRpU29jInRKNLDionDzYc6T9e7GQcH4hoHGx9kWgLC-ouI06HRtYi-EOYcQIDLTjyLUhhD9zPCG_aLJ0zRwZXHa7YLf3C",
//                    notification = NotificationBody(
//                        title = "Antrii",
//                        body = "asdasdsad",
//                        mutable_content = 1,
//                        sound = "Tri-tone"
//                    )
//                )
//            )
//        }
    }

}