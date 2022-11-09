package com.github.shaggydemiurge.movieapp

import android.app.Application
import com.github.shaggydemiurge.movieapp.data.dataModule
import com.github.shaggydemiurge.movieapp.domain.domainModule
import com.github.shaggydemiurge.movieapp.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            androidContext(this@App)
            modules(
                appModule,
                dataModule,
                domainModule,
                presentationModule
            )
        }
    }
}
