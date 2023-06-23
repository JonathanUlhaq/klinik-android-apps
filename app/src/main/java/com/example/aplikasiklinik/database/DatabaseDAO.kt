package com.example.aplikasiklinik.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.model.ThemeModeModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.POST

@Dao
interface DatabaseDAO {
    @Query("SELECT * FROM theme_tb ")
    fun getThemeBoolean():Flow<List<ThemeModeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBoolean(model:ThemeModeModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBoolean(model: ThemeModeModel)

    @Query("DELETE FROM theme_tb")
    suspend fun deleteAll()

    @Query("SELECT * FROM LoginSaver")
    fun getUserInfo():Flow<List<LoginSaver>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(model:LoginSaver)

    @Query("DELETE FROM LoginSaver")
    suspend fun logoutApp()

}