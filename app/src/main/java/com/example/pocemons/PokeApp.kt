package com.example.pocemons

import android.app.Application
import com.example.pocemons.di.AppComponent
import com.example.pocemons.di.DaggerAppComponent

class PokeApp: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .applicationContext(this)
            .build()
    }
}