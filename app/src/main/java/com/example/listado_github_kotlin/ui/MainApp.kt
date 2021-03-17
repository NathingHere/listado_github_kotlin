package com.example.listado_github_kotlin.ui

import android.app.Application
import com.example.listado_github_kotlin.ui.Koin.domainKoin
import com.example.listado_github_kotlin.ui.Koin.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApp)
            androidLogger()
            modules(listOf(domainKoin, viewModelModule))
        }

    }

}