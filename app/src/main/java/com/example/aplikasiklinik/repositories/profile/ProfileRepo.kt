package com.example.aplikasiklinik.repositories.profile

import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.network.APIEndpoint
import javax.inject.Inject

class ProfileRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun getProfile():User = api.getProfile()
}