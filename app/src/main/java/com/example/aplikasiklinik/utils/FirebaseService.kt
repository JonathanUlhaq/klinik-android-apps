package com.example.aplikasiklinik.utils

import android.content.ContentValues
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FirebaseService:FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(ContentValues.TAG, "Refreshed token: $token")

    }
}