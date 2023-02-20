package com.example.aplikasiklinik.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.aplikasiklinik.MainActivity
import com.example.aplikasiklinik.R

class NotificationManager (val context: Context, val desc:String) {

    val channelId = "FCM100"
    val channelName = "FCMMessage"

    lateinit var notificationChannel: NotificationChannel
    lateinit var notificationBuilder:NotificationCompat.Builder
    val notificationManager = context.applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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
        notificationManager.notify(100,notificationBuilder.build())
    }
}
