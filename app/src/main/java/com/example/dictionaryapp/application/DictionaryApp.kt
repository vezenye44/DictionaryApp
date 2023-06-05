package com.example.dictionaryapp.application

import android.app.Application
import com.example.dictionaryapp.di.application
import com.example.dictionaryapp.di.historyScreen
import com.example.dictionaryapp.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            this.androidContext(this@DictionaryApp)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}