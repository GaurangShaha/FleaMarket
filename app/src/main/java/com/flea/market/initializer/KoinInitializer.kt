package com.flea.market.initializer

import android.content.Context
import androidx.startup.Initializer
import com.flea.market.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin

internal class KoinInitializer : Initializer<KoinApplication> {
    override fun create(context: Context) = startKoin {
        androidLogger()
        androidContext(context)
        modules(appModule)
    }

    override fun dependencies() = mutableListOf<Class<out Initializer<*>>>()
}
