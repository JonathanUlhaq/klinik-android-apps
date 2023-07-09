package com.example.aplikasiklinik.network

import com.example.aplikasiklinik.model.notification.PushNotification
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface NotifEndpoint {

    @Headers(
        "Content-Type: application/json",
        "Authorization: key=AAAAFzUIttE:APA91bG78xoZGQPfr5c5GSRlXXQlMW_U8Pm2HGW34-Sml4MSMFlEIdP9yWUr_ueRG3H1fxFhkkWfROm-Zt3oiMl0cjc7wolmUTQzzOGgyrLmQInFD06x8-1k7nPgCg5DyQ0ERdbP9Arj"
    )
    @POST("fcm/send")
    suspend fun sendNotification(@Body notification:PushNotification)

}