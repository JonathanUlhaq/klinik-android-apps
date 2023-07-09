package com.example.aplikasiklinik.model.notification

data class NotificationBody(
    val title: String,
    val body: String,
    val mutable_content: Int,
    val sound: String
)
