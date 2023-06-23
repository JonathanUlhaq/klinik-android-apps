package com.example.aplikasiklinik.view.antrian

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.repositories.profile.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AntrianViewModel @Inject constructor(private val repo: ProfileRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<User>(User())
    val uiState = _uiState.asStateFlow()

    fun getProfile(
        isError: MutableState<Boolean>,
        isLoading: MutableState<Boolean>
    ) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repo.getProfile().let {user ->
                    _uiState.value = user
                    isLoading.value = false
                    isError.value = false
                }
            } catch (e: Exception) {
                isLoading.value = false
                isError.value = true
                Log.e("GET PROFILE ERROR ", e.toString())
            }
        }
}