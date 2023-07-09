package com.example.aplikasiklinik.di

import android.content.Context
import androidx.room.Room
import com.example.aplikasiklinik.database.Database
import com.example.aplikasiklinik.database.DatabaseDAO
import com.example.aplikasiklinik.network.APIEndpoint
import com.example.aplikasiklinik.network.AuthInterceptor
import com.example.aplikasiklinik.url.URL
import com.example.aplikasiklinik.utils.ConstUrl
import com.example.aplikasiklinik.utils.TokenManager
import com.google.gson.GsonBuilder
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

    @Singleton
    @Provides
    fun okhtppProvider(authInterceptor: AuthInterceptor,getToken:TokenManager):OkHttpClient {
        val token = getToken.getToken()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()
    }

    @Singleton
    @Provides
    fun retrofitProvider(client: OkHttpClient): APIEndpoint =
        Retrofit.Builder()
            .baseUrl(ConstUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(APIEndpoint::class.java)


    @Singleton
    @Provides
    fun provideDAO(database:Database):DatabaseDAO {
        return database.dao()
    }

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context):Database {
      return  Room.databaseBuilder(
            context,
            Database::class.java,
            "db_local"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideContext(@ApplicationContext context: Context):Context =
        context

}