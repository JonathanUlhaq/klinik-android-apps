package com.example.aplikasiklinik.database

import androidx.room.RoomDatabase
import com.example.aplikasiklinik.model.ThemeModeModel

@androidx.room.Database(entities = [ThemeModeModel::class], version = 4)
abstract class Database:RoomDatabase() {
    abstract fun dao():DatabaseDAO
}