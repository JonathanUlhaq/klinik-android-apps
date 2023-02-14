package com.example.aplikasiklinik.view.profil

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfilViewModel @Inject constructor() : ViewModel() {
    val nik = mutableStateOf("01293123982")
    val name = mutableStateOf("Ragnar Holbrook")
    val address = mutableStateOf("Magetan")
    val phone = mutableStateOf("08129292")
    val date = mutableStateOf("03/03/2023")
    val edit = mutableStateOf(false)
}