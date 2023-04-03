package com.example.aplikasiklinik.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun networkChecker(context:Context):Boolean {
val infoNetwork:ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = infoNetwork.activeNetwork ?: return false
    val networkCapabilities = infoNetwork.getNetworkCapabilities(network)?:return false
    return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}