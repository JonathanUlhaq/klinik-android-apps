package com.example.aplikasiklinik.repositories.pasienLogin

import com.example.aplikasiklinik.database.DatabaseDAO
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.network.APIEndpoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PasLogRepo @Inject constructor(private val api:APIEndpoint, private val dao:DatabaseDAO) {
    suspend fun loginPasien(telepon:String) = api.getAllData(telepon)
    fun getLoginPasien(): Flow<List<LoginSaver>> = dao.getUserInfo()
    suspend fun getOtp(otp:String) = api.getOTP(otp)
    suspend fun insertLoginInfo(model:LoginSaver) = dao.insertUserInfo(model)
    suspend fun logoutApp() = dao.logoutApp()
}