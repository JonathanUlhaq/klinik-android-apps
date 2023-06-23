package com.example.aplikasiklinik.model.currentantrian

data class AntriSekarangResponse(
    val message:String? = null,
    val no_bpjs:String? = null,
    val nomer_antrian:Int? = null,
    val kurang_antrian:Int? = null,
    val data:CurAntData? = null

)
