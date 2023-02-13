package com.example.aplikasiklinik.view.otp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OTPViewModel @Inject constructor():ViewModel() {
    var otpValue = mutableStateOf("")
}