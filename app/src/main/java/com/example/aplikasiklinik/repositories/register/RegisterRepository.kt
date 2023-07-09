package com.example.aplikasiklinik.repositories.register

import com.example.aplikasiklinik.model.register.RegisterResponse
import com.example.aplikasiklinik.network.APIEndpoint
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class RegisterRepository @Inject constructor(private val api: APIEndpoint) {
    suspend fun registerAccount(
        foto:MultipartBody.Part,
        name: RequestBody,
        telepon: RequestBody,
        tanggal_lahir: RequestBody,
        alamat: RequestBody,
        no_bpjs:RequestBody,
        password:RequestBody
    ):RegisterResponse = api.daftarAkun(
        foto,
        name,
        telepon,
        tanggal_lahir,
        alamat,
        no_bpjs,
        password
    )
}