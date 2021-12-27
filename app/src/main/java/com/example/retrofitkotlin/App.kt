package com.example.retrofitkotlin

import android.app.Application
import com.example.retrofitkotlin.servicelocator.networkModule
import com.example.retrofitkotlin.servicelocator.repositoriesModule
import com.example.retrofitkotlin.servicelocator.viewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(networkModule, repositoriesModule, viewModelsModule)
        }

    }
}