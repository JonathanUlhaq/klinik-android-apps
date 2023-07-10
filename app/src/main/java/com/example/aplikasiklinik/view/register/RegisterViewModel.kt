package com.example.aplikasiklinik.view.register

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.register.RegisterResponse
import com.example.aplikasiklinik.repositories.register.RegisterRepository
import com.example.aplikasiklinik.utils.SharePrefrence
import com.example.aplikasiklinik.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repo: RegisterRepository,
    val pref: SharePrefrence,
    private val token: TokenManager
) : ViewModel() {
    private val _uiState = MutableStateFlow<RegisterResponse>(RegisterResponse())
    val uiState = _uiState.asStateFlow()

    fun registerAkun(
        foto:MultipartBody.Part,
        nama: RequestBody,
        telepon: RequestBody,
        tanggal_lahir: RequestBody,
        alamat: RequestBody,
        no_bpjs: RequestBody,
        password: RequestBody,
        deviceId:RequestBody,
        isLoading: MutableState<Boolean>,
        isError: MutableState<Boolean>,
        action: (RegisterResponse) -> Unit
    ) =
        viewModelScope.launch {
            isLoading.value = true
            try {
                repo.registerAccount(
                    foto,
                    name = nama,
                    telepon = telepon,
                    tanggal_lahir = tanggal_lahir,
                    alamat = alamat,
                    no_bpjs = no_bpjs,
                    password = password,
                    device_id = deviceId
                ).let {
                    pref.saveNumber(it.user?.telepon!!)
                    token.saveToken(it.access_token!!)
                    isLoading.value = false
                    if (!isLoading.value) {
                        action.invoke(it)
                    }
                    isError.value = false

                }
            } catch (e: Exception) {
                isLoading.value = false
                isError.value = true
                Log.d("REGISTER ERORR WOI ", e.toString())
            }
        }
}