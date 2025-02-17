package com.flea.market.data.di

import android.artisan.networking.retrofit.remote.ResultCallAdapterFactory
import com.flea.market.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://fakestoreapi.com"

internal val networkModule = module {
    single<Retrofit> {
        Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ResultCallAdapterFactory.create())
            .client(get()).build()
    }

    single {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) okHttpClientBuilder.addInterceptor(get<HttpLoggingInterceptor>())
        okHttpClientBuilder.build()
    }

    single {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }
}
