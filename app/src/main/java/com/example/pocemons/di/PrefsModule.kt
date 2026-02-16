package com.example.pocemons.di

import android.content.Context
import com.example.pocemons.utils.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PrefsModule {

    @Singleton
    @Provides
    fun providePrefs(context: Context): Prefs {
        return Prefs(context)
    }
}