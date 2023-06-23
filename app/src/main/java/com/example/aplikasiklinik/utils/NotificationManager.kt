package com.example.aplikasiklinik.utils

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStateAtLeast
import com.example.aplikasiklinik.MainActivity
import com.example.aplikasiklinik.R
import kotlinx.coroutines.launch
import kotlin.random.Random

class NotificationManager (val context: Context, private val desc:String) {

    private val channelId = "FCM1000"
    private val channelName = "FCMMessage"

    private lateinit var notificationChannel: NotificationChannel
    private lateinit var notificationBuilder:NotificationCompat.Builder
    private val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun notificationManager() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel = NotificationChannel(channelId,channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(context,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)
        notificationBuilder = NotificationCompat.Builder(context,channelId)
        notificationBuilder.setSmallIcon(R.drawable.notif_triyola)
        notificationBuilder.addAction(R.drawable.logo_triyola,"Buka Aplikasi",pendingIntent)
        notificationBuilder.setContentTitle("Antrian Klinik Triyola")
        notificationBuilder.setContentText(desc)
        notificationBuilder.setAutoCancel(true)
        notificationBuilder.setContentIntent(pendingIntent)
        notificationBuilder.setOnlyAlertOnce(true)
        notificationBuilder.priority = NotificationCompat.PRIORITY_HIGH

        notificationManager.notify(Random.nextInt(),notificationBuilder.build())

//        val notification: Notification = NotificationCompat.Builder(this, channelId)
//            .setContentTitle("Antrian Klinik Triyola")
//            .setSmallIcon(R.drawable.notif_triyola)
//            .setContentText(desc)
//            .addAction(R.drawable.logo_triyola,"Buka Aplikasi",getPendingIntent())
//            .setContentIntent(getPendingIntent())
//            .setOnlyAlertOnce(true)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .build()
    }
}

//    class MyForegroundService(val context: Context, private val desc:String)  : LifecycleService() {
//        private lateinit var notificationManager: NotificationManager
//
//        private val channelId = "TK100"
//        private val channelName = "TKMessage"
//        override fun onCreate() {
//            super.onCreate()
//            notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            startForeground(1, createNotification(context,desc))
//
//        }
//
//    private fun createNotification(context: Context,desc:String): Notification {
//
//        val intent = Intent(context,MainActivity::class.java)
//        val pendingIntent = PendingIntent.getActivity(context,0,intent,PendingIntent.FLAG_IMMUTABLE)
//
//            val notification: Notification = NotificationCompat.Builder(this, channelId)
//            .setContentTitle("Antrian Klinik Triyola")
//            .setSmallIcon(R.drawable.notif_triyola)
//            .setContentText(desc)
//            .addAction(R.drawable.logo_triyola,"Buka Aplikasi",pendingIntent)
//            .setContentIntent(pendingIntent)
//            .setOnlyAlertOnce(false)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .build()
//        return notification
//    }
//    }



