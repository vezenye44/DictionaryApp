package com.example.dictionaryapp.application

import android.app.Application
import com.example.dictionaryapp.di.application
import com.example.dictionaryapp.di.mainScreen
import org.koin.core.context.startKoin

class DictionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}