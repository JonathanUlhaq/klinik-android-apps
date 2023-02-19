package com.example.aplikasiklinik.network

import com.example.aplikasiklinik.model.DataPasien
import retrofit2.http.GET

interface APIEndpoint {

    @GET("")
    suspend fun getAllData():DataPasien

}