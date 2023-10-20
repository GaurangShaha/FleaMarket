package com.flea.market.data.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://fakestoreapi.com"

val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(MoshiConverterFactory.create())
            .client(get()).build()
    }

    single {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }
}