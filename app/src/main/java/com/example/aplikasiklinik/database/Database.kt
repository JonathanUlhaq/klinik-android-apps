package com.example.aplikasiklinik.database

import androidx.room.RoomDatabase
import com.example.aplikasiklinik.model.LoginSaver
import com.example.aplikasiklinik.model.ThemeModeModel

@androidx.room.Database(entities = [ThemeModeModel::class,LoginSaver::class], version = 9)
abstract class Database:RoomDatabase() {
    abstract fun dao():DatabaseDAO
}