package com.example.aplikasiklinik.model.register

data class RegisterResponse(
    val user:UserRegister? = null,
    val access_token:String? = null,
    val token_type:String? = null
)
