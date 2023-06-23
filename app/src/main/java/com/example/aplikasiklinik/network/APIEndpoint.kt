package com.example.aplikasiklinik.network

import com.example.aplikasiklinik.model.DataPasien
import com.example.aplikasiklinik.model.batalantri.BatalAntriResponse
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.model.daftarantri.DafAntriResponse
import com.example.aplikasiklinik.model.kunjungan.KunjugnanResponse
import com.example.aplikasiklinik.model.pasien.PasienResponse
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.model.register.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import javax.inject.Singleton

@Singleton
interface APIEndpoint {

    @FormUrlEncoded
    @POST("api/login")
    suspend fun getAllData(
        @Field("otp") otp: String): PasienResponse

    @PUT("api/pasien/batal-antrian")
    suspend fun batalAntri():BatalAntriResponse

    @GET("api/profile")
    suspend fun getProfile():User

    @FormUrlEncoded
    @POST("api/otp")
    suspend fun getOTP(
        @Field("telepon")otp:String
    )

    @FormUrlEncoded
    @POST("api/pasien/antrian")
    suspend fun daftarAntrian(
        @Field("jenis_poli") jenis_poli: String,
        @Field("keluhan") keluhan: String,
        @Field("alergi")alergi:String
    ):DafAntriResponse

    @FormUrlEncoded
    @POST("api/register")
    suspend fun daftarAkun(
        @Field("name")name:String,
        @Field("telepon")telepon:String,
        @Field("tanggal_lahir")tanggal_lahir:String,
        @Field("alamat")alamat:String,
        @Field("no_bpjs")no_bpjs:String,
        @Field("password")password:String
    ):RegisterResponse

    @GET("api/pasien/antrianku")
    suspend fun getCurrentAntrian():AntriSekarangResponse

    @GET("api/pasien/kunjunganku")
    suspend fun getRiwayatAntri():KunjugnanResponse



}