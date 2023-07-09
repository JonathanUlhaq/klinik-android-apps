package com.example.aplikasiklinik.view.otp

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.model.pasien.PasienResponse
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.repositories.pasienLogin.PasLogRepo
import com.example.aplikasiklinik.utils.ConstUrl
import com.example.aplikasiklinik.utils.SharePrefrence
import com.example.aplikasiklinik.utils.TokenManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor(val pref:SharePrefrence,val repo:PasLogRepo,private val tokene:TokenManager):ViewModel() {

    fun addToken(token: String) {
        tokene.saveToken(token)
    }

    fun getOtp(otp: String,event:() -> Unit) {
        viewModelScope.launch {
            try {
                repo.getOtp(otp).let {
                    event.invoke()
                }
            } catch (e: Exception) {
                Log.e("ERROR OTP ", e.toString())
            }
        }
    }

    fun getNumber():String? {
        return pref.getNumber()
    }
    fun addLoginStatus(model: LoginSaver) =
        viewModelScope.launch {
            repo.insertLoginInfo(model)
        }
    fun loginPasien(
        isError: MutableState<Boolean>,
        isLoading: MutableState<Boolean>,
        otp: String,
        resp: (User?, String?) -> Unit
    ) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repo.loginPasien(otp).let { response ->
                    resp.invoke(response.user, response.access_token)
                    pref.saveId(response.user?.id!!.toString())
                    pref.saveBPJS(response.user.no_bpjs.toString())
                    pref.saveNama(response.user.name.toString())
                    pref.saveLahir(response.user.tanggal_lahir.toString())
                    pref.saveTelepon(response.user.telepon.toString())
                    pref.saveAlamat(response.user.alamat.toString())
                    pref.saveFoto("${ConstUrl.BASE_URL}${response.user.foto.toString()}")
                    tokene.saveToken(response.access_token!!)
                    Log.d("TOKENNYA MASSE ",tokene.getToken()!!)
                    isLoading.value = false
                }
            } catch (e: Exception) {
                isLoading.value = false
                isError.value = true
                Log.e("ERROR Log In Response ", e.toString())
            }
        }
}