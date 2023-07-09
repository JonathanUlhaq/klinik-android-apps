package com.example.aplikasiklinik.utils

import android.content.ContentValues
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseService:FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(ContentValues.TAG, "Refreshed token: $token")

    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        val notification = NotificationManager(this, message.notification?.body.toString())
        notification.notificationManager()
    }
}