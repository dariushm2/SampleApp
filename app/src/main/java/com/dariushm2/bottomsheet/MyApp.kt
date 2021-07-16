package com.dariushm2.bottomsheet

import android.app.Application
import com.dariushm2.bottomsheet.di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MyApp : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(appModule)
        }
    }
}