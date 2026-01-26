package com.setiawan.capstone

import android.app.Application
import com.setiawan.capstone.core.di.databaseModule
import com.setiawan.capstone.core.di.networkModule
import com.setiawan.capstone.core.di.repositoryModule
import com.setiawan.capstone.di.useCaseModule
import com.setiawan.capstone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}