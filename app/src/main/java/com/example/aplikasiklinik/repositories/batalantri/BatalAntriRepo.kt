package com.example.aplikasiklinik.repositories.batalantri

import com.example.aplikasiklinik.model.batalantri.BatalAntriResponse
import com.example.aplikasiklinik.network.APIEndpoint
import javax.inject.Inject

class BatalAntriRepo @Inject constructor(private val api:APIEndpoint) {
    suspend fun batalAntri():BatalAntriResponse = api.batalAntri()
}