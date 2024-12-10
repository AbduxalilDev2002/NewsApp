package com.abduxalil.dev.mynewsapi.app

import android.app.Application
import com.abduxalil.dev.mynewsapi.app.di.networkModule
import com.abduxalil.dev.mynewsapi.app.di.repositoryModule
import com.abduxalil.dev.mynewsapi.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin for dependency injection
        startKoin {
            androidContext(this@MyApplication)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule,
                )
            )
        }
    }
}