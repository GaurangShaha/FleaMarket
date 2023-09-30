package com.flea.market.data.common.di

import com.flea.market.data.common.local.DatabaseProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal val dbModule = module { single { DatabaseProvider.getDatabase(androidApplication()) } }

private const val BASE_URL = "https://fakestoreapi.com"

internal val networkModule = module {
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

val baseDataModule = dbModule + networkModule