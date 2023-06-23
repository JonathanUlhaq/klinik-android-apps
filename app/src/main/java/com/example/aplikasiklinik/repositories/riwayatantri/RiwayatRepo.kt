package com.example.aplikasiklinik.repositories.riwayatantri

import com.example.aplikasiklinik.model.kunjungan.KunjugnanResponse
import com.example.aplikasiklinik.network.APIEndpoint
import javax.inject.Inject

class RiwayatRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun getAllRiwayatAntri():KunjugnanResponse = api.getRiwayatAntri()
}