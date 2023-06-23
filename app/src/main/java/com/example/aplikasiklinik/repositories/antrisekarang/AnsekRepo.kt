package com.example.aplikasiklinik.repositories.antrisekarang

import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.network.APIEndpoint
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnsekRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun getAntriSek():AntriSekarangResponse = api.getCurrentAntrian()
}