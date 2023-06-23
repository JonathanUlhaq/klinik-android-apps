package com.example.aplikasiklinik.repositories.register

import com.example.aplikasiklinik.model.register.RegisterResponse
import com.example.aplikasiklinik.network.APIEndpoint
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: APIEndpoint) {
    suspend fun registerAccount(
        name: String,
        telepon: String,
        tanggal_lahir: String,
        alamat: String,
        no_bpjs:String,
        password:String
    ):RegisterResponse = api.daftarAkun(
        name,
        telepon,
        tanggal_lahir,
        alamat,
        no_bpjs,
        password
    )
}