package com.example.aplikasiklinik.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoginSaver(
    @PrimaryKey
    val id:Int = 0,
    val name:String = "",
    val telepon:String = "",
    val alamat:String = "",
    val jenis_kelamin:String = "",
    val foto:String = "",
    val no_bpjs:String = "",
    val rekam_medis_id:Int = 0,
    val tanggal_lahir:String = ""

)
