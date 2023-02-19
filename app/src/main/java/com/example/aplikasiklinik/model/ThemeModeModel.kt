package com.example.aplikasiklinik.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "theme_tb")
data class ThemeModeModel(
    @PrimaryKey
    @ColumnInfo(name = "id")
    var id:Int = 0,
    @ColumnInfo(name = "darkMode")
    var darkMode:Boolean = true
)
