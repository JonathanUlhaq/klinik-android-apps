package com.example.aplikasiklinik.widget.register

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() :ViewModel() {
    val nik = mutableStateOf("")
    val name = mutableStateOf("")
    val address = mutableStateOf("")
    val phone = mutableStateOf("")
    val date = mutableStateOf("")
    val hide = mutableStateOf(false)
}