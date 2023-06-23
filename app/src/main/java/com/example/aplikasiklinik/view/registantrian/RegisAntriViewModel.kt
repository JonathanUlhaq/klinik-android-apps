package com.example.aplikasiklinik.view.registantrian

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.repositories.daftarantri.DafAntriRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisAntriViewModel @Inject constructor(private val repo:DafAntriRepo):ViewModel() {
    fun daftarAntrian(context:Context,isEmpty:MutableState<Boolean>,isError:MutableState<Boolean>,boolean:MutableState<Boolean>,poli:String,keluhan:String,alergi:String,response:(String?) -> Unit) =
        viewModelScope.launch {
           try {
               boolean.value = true
               repo.daftarAntri(poli, keluhan, alergi).let {
                   response.invoke(it.msg)
                   boolean.value = false
               }
           } catch (e:Exception) {
               Log.e("ERROR DAFTAR ANTRI ",e.toString())
               boolean.value = false
               isError.value = true
               isEmpty.value = false
               Toast.makeText(context,"Anda sudah mengantri",Toast.LENGTH_SHORT).show()
           }
        }
}