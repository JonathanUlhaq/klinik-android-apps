package com.example.aplikasiklinik.view.profil

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.update.UpdateResponse
import com.example.aplikasiklinik.repositories.updateprofile.UpdateProfileRepo
import com.example.aplikasiklinik.utils.SharePrefrence
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor(val repo : UpdateProfileRepo, val pref:SharePrefrence) : ViewModel() {
    val nik = mutableStateOf("01293123982")
    val name = mutableStateOf("Ragnar Holbrook")
    val address = mutableStateOf("Magetan")
    val phone = mutableStateOf("08129292")
    val date = mutableStateOf("03/03/2023")
    val edit = mutableStateOf(false)

    fun getId():String? =
        pref.getId()

    fun sharePref():SharePrefrence =
        pref

    fun updateProfile(
        id:Int,
        foto: MultipartBody.Part,
        name: RequestBody,
        telepon: RequestBody,
        tanggal_lahir: RequestBody,
        alamat: RequestBody,
        no_bpjs:RequestBody,
        password: RequestBody,
        isLoading:MutableState<Boolean>,
        eventError:() -> Unit,
        event:(UpdateResponse) -> Unit
        )  =
        viewModelScope.launch {
            try {
            isLoading.value = true
                repo.updateProfile(
                    id = id,
                    foto = foto,
                    name = name,
                    telepon = telepon,
                    tanggal_lahir = tanggal_lahir,
                    alamat = alamat,
                    no_bpjs = no_bpjs,
                    password = password
                ).let {
                    isLoading.value = false
                    event.invoke(it)
                    Log.e("Update BERHASIL ","SANGAR")
                }
            } catch (e:Exception) {
                isLoading.value = false
                eventError.invoke()
                Log.e("Update Error ",e.toString())
            }
        }



}