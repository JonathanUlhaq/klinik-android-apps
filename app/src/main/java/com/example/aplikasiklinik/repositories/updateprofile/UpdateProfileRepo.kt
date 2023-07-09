package com.example.aplikasiklinik.repositories.updateprofile

import com.example.aplikasiklinik.model.update.UpdateResponse
import com.example.aplikasiklinik.network.APIEndpoint
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class UpdateProfileRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun updateProfile(
        id:Int,
        foto:MultipartBody.Part,
        name: RequestBody,
        telepon: RequestBody,
        tanggal_lahir: RequestBody,
        alamat: RequestBody,
        no_bpjs:RequestBody,
        password: RequestBody
    ):UpdateResponse = api.updateProfile(
        id = id,
        foto = foto,
        name = name,
        telepon = telepon,
        tanggal_lahir = tanggal_lahir,
        alamat = alamat,
        no_bpjs = no_bpjs,
        password = password
    )
}