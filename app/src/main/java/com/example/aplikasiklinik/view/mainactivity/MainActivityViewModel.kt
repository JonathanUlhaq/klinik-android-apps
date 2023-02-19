package com.example.aplikasiklinik.view.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aplikasiklinik.model.ThemeModeModel
import com.example.aplikasiklinik.repositories.local.ThemeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(val repo: ThemeRepo):ViewModel() {
    private val _uiState = MutableStateFlow<List<ThemeModeModel>>(emptyList())
    val uiState = _uiState.asStateFlow()

    fun getBooleanData() {
        viewModelScope.launch {
           repo.getThemeBoolean().collect{
               _uiState.value = it
           }
        }
    }

    fun insertBoolean(model:ThemeModeModel) {
        viewModelScope.launch {
            repo.insertThemeBoolean(model)
        }
    }

    fun updateBoolean(model:ThemeModeModel) {
        viewModelScope.launch {
            repo.updateThemeBoolean(model)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            deleteAll()
        }
    }

    init {
        getBooleanData()
    }

}