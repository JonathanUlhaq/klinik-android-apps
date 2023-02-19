package com.example.aplikasiklinik.di

import android.content.Context
import androidx.room.Room
import com.example.aplikasiklinik.database.Database
import com.example.aplikasiklinik.database.DatabaseDAO
import com.example.aplikasiklinik.network.APIEndpoint
import com.example.aplikasiklinik.url.URL
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@dagger.Module
class Module {

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
    fun retrofitEndpoint(@ApplicationContext context:Context):APIEndpoint {
       return Retrofit.Builder()
            .baseUrl(URL.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
           .create(APIEndpoint::class.java)

    }

}