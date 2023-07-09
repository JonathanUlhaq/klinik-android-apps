package com.example.aplikasiklinik.view.antrian

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.repositories.profile.ProfileRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AntrianViewModel @Inject constructor(private val repo: ProfileRepo) : ViewModel() {
    private val _uiState = MutableStateFlow<User>(User())
    val uiState = _uiState.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun getProfile(
        isError: MutableState<Boolean> = mutableStateOf(false),
        isLoading: MutableState<Boolean> = mutableStateOf(false),
        event:() -> Unit = {}
    ) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                repo.getProfile().let {user ->
                    _uiState.value = user
                    isLoading.value = false
                    isError.value = false
                    event.invoke()
                }
            } catch (e: Exception) {

                isLoading.value = false
                isError.value = true
                event.invoke()
                Log.e("GET PROFILE ERROR ", e.toString())
            }
        }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getProfile {
                viewModelScope.launch {
                    _isRefreshing.emit(false)
                }
            }
        }
    }
}