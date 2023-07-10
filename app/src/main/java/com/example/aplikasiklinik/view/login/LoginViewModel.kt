package com.example.aplikasiklinik.view.login

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.model.pasien.PasienResponse
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.repositories.pasienLogin.PasLogRepo
import com.example.aplikasiklinik.utils.SharePrefrence
import com.example.aplikasiklinik.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repo: PasLogRepo,
    private val pref: TokenManager,
    private val prefNum:SharePrefrence
) : ViewModel() {

    private val _uiState = MutableStateFlow<List<LoginSaver>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun addToken(token: String) {
        pref.saveToken(token)
    }

    fun addNumber(number: String) {
        prefNum.saveNumber(number)
    }

    fun getOtp(isError: MutableState<Boolean> = mutableStateOf(false), isLoading: MutableState<Boolean> = mutableStateOf(false), otp: String, event:() -> Unit) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                repo.getOtp(otp).let {
                    if (it.success == false) {
                        isLoading.value = false
                        isError.value = true
                    } else {
                        isLoading.value = false
                        event.invoke()
                        isError.value = false
                    }

                }
            } catch (e: Exception) {
                 isLoading.value = false
                 isError.value = true
                Log.e("ERROR OTP ", e.toString())
            }
        }
    }

    fun getToken(): String? =
        pref.getToken()



    fun getLoginStatus() =
        viewModelScope.launch {
            repo.getLoginPasien().collect { list ->
                if (list.isNotEmpty()) {
                    _uiState.value = list
                } else {
                    _uiState.value = emptyList()
                }
            }
        }

    fun addLoginStatus(model: LoginSaver) =
        viewModelScope.launch {
            repo.insertLoginInfo(model)
        }

    fun deleteLoginStatus() =
        viewModelScope.launch {
            repo.logoutApp()
        }



}