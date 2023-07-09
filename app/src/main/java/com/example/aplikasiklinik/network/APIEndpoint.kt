package com.example.aplikasiklinik.network

import com.example.aplikasiklinik.model.DataPasien
import com.example.aplikasiklinik.model.batalantri.BatalAntriResponse
import com.example.aplikasiklinik.model.currentantrian.AntriSekarangResponse
import com.example.aplikasiklinik.model.daftarantri.DafAntriResponse
import com.example.aplikasiklinik.model.kunjungan.KunjugnanResponse
import com.example.aplikasiklinik.model.pasien.PasienResponse
import com.example.aplikasiklinik.model.pasien.User
import com.example.aplikasiklinik.model.register.RegisterResponse
import com.example.aplikasiklinik.model.update.DataUpdate
import com.example.aplikasiklinik.model.update.UpdateResponse
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface APIEndpoint {

    @FormUrlEncoded
    @POST("api/login")
    suspend fun getAllData(
        @Field("otp") otp: String): PasienResponse

    @Multipart
    @POST("/api/profile/{id}?_method=PUT")
    suspend fun updateProfile(
        @Path("id")id:Int,
        @Part foto:MultipartBody.Part,
        @Part("name")name:RequestBody,
        @Part("telepon")telepon:RequestBody,
        @Part("tanggal_lahir")tanggal_lahir:RequestBody,
        @Part("alamat")alamat:RequestBody,
        @Part("no_bpjs")no_bpjs:RequestBody,
        @Part("password")password:RequestBody
    ):UpdateResponse

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

    @Multipart
    @POST("api/register")
    suspend fun daftarAkun(
        @Part foto:MultipartBody.Part,
        @Part("name")name:RequestBody,
        @Part("telepon")telepon:RequestBody,
        @Part("tanggal_lahir")tanggal_lahir:RequestBody,
        @Part("alamat")alamat:RequestBody,
        @Part("no_bpjs")no_bpjs:RequestBody,
        @Part("password")password:RequestBody
    ):RegisterResponse

    @GET("api/pasien/antrianku")
    suspend fun getCurrentAntrian():AntriSekarangResponse

    @GET("api/pasien/kunjunganku")
    suspend fun getRiwayatAntri():KunjugnanResponse



}