package com.example.aplikasiklinik.view.antrianhistory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.kunjungan.KunjugnanResponse
import com.example.aplikasiklinik.repositories.riwayatantri.RiwayatRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RiwayatAnViewModel @Inject constructor(private val repo:RiwayatRepo):ViewModel() {
    private val _uiState = MutableStateFlow<KunjugnanResponse>(KunjugnanResponse())
    val uiState = _uiState.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()
    fun getRiwayatData(event:() -> Unit = {}) =
        viewModelScope.launch {
            try {
                _uiState.value = repo.getAllRiwayatAntri()
                event.invoke()
            } catch (e:Exception) {
                Log.e("GET RIWAYAT ANTRI ERROR ",e.toString())
                event.invoke()
            }
        }
    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getRiwayatData {
                viewModelScope.launch {
                    _isRefreshing.emit(false)
                }
            }
        }
    }
    init {
        getRiwayatData()
    }
}