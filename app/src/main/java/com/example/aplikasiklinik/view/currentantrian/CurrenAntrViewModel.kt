package com.example.aplikasiklinik.view.currentantrian

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.batalantri.BatalAntriResponse
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.repositories.antrisekarang.AnsekRepo
import com.example.aplikasiklinik.repositories.batalantri.BatalAntriRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenAntrViewModel @Inject constructor(
    private val repo: AnsekRepo,
    private val batalAntri: BatalAntriRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow<AntriSekarangResponse>(AntriSekarangResponse())
    val uiState = _uiState.asStateFlow()
    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()
    fun getCurrentAntri(
        isLoading: MutableState<Boolean> = mutableStateOf(false),
        isError: MutableState<Boolean> = mutableStateOf(false),
        event:() -> Unit = {}
    ) =
        viewModelScope.launch {
            try {
                _uiState.value = repo.getAntriSek()
                if (_uiState.value.kurang_antrian != null) {
                    isLoading.value = false
                    event.invoke()
                }
                isError.value = false

            } catch (e: Exception) {
                Log.e("ERROR GET CURRENT ANTRIAN ", e.toString())
                event.invoke()
                isLoading.value = false
                isError.value = true

            }
        }

    fun refresh() {
        viewModelScope.launch {
            _isRefreshing.emit(true)
            getCurrentAntri {
                viewModelScope.launch {
                    _isRefreshing.emit(false)
                }
            }
        }
    }

    fun batalAntrian(
        isLoading: MutableState<Boolean>,
        isError: MutableState<Boolean>,
        action: () -> Unit
    ) =
        viewModelScope.launch {
            try {
                isLoading.value = true
                batalAntri.batalAntri().let {
                    isLoading.value = false
                    action.invoke()
                }
            } catch (e: Exception) {
                isLoading.value = false
                isError.value = true
                Log.e("BATAL ANTRI ERROR ", e.toString())
            }
        }


}