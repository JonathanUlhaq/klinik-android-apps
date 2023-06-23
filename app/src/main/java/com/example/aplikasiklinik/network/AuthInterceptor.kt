package com.example.aplikasiklinik.network

import android.util.Log
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.model.pasien.PasienResponse
import com.example.aplikasiklinik.utils.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor():Interceptor {

    @Inject
    lateinit var getToken:TokenManager

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = getToken.getToken()
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")
            .build()
        return chain.proceed(modifiedRequest)
    }

}