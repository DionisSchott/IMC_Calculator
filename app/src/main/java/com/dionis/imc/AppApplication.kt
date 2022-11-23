package com.dionis.imc

import android.app.Application
import com.dionis.imc.di.daoModule
import com.dionis.imc.di.repositoryModule
import com.dionis.imc.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule)
            modules(repositoryModule)
            modules(daoModule)
        }
    }
}