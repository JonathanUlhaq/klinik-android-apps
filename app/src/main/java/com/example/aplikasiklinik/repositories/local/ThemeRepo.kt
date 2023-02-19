package com.example.aplikasiklinik.repositories.local

import com.example.aplikasiklinik.database.DatabaseDAO
import com.example.aplikasiklinik.model.ThemeModeModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ThemeRepo @Inject constructor(val dao: DatabaseDAO) {
    fun getThemeBoolean():Flow<List<ThemeModeModel>> = dao.getThemeBoolean()
    suspend fun insertThemeBoolean(model: ThemeModeModel) = dao.insertBoolean(model)
    suspend fun updateThemeBoolean(model: ThemeModeModel) = dao.updateBoolean(model)
    suspend fun deleteAll() = dao.deleteAll()
}