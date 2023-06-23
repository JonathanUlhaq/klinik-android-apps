package com.example.aplikasiklinik.repositories.daftarantri

import com.example.aplikasiklinik.network.APIEndpoint
import javax.inject.Inject

class DafAntriRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun daftarAntri(poli:String,keluhan:String,alergi:String)
    = api.daftarAntrian(poli,keluhan,alergi)
}