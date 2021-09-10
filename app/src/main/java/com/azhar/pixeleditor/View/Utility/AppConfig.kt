package com.azhar.pixeleditor.View.Utility

import android.app.Application
import com.azhar.pixeleditor.DependencyInjection.repositoryModule
import com.azhar.pixeleditor.DependencyInjection.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

@Suppress("unused")
class AppConfig:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(listOf(repositoryModule, viewModelModule))
        }
    }
}