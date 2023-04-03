package com.example.aplikasiklinik

import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.aplikasiklinik.database.Database
import com.example.aplikasiklinik.database.DatabaseDAO
import com.example.aplikasiklinik.model.ThemeModeModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao:DatabaseDAO
    private lateinit var database:Database

    @Before
    fun setup () {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            Database::class.java
        ).build()
        dao = database.dao()
    }

    @After
    fun afterRun() {
        database.close()
    }

    @Test
    fun inputData() {
        runBlockingTest {
            val model = ThemeModeModel(0,true)
            dao.insertBoolean(model)
            val check = dao.getThemeBoolean().first().contains(model)
            if (check) {
                Log.d("Ada: ","true")
            } else {
                Log.d("Ada: ","false")
            }
        }
    }

}