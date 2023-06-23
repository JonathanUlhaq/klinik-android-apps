package com.example.aplikasiklinik.model.pasien

import java.util.Date

data class User (
    val id:Int? = null,
    val no_bpjs:String? = null,
    val rekam_medis_id:String? = null,
    val role_id:String? = null,
    val name:String? = null,
    val email:String? = null,
    val telepon:String? = null,
    val alamat:String? = null,
    val jenis_kelamin:String? = null,
    val foto:String? = null,
    val tanggal_lahir:String? = null
        )
