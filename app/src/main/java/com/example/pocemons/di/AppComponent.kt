package com.example.pocemons.di

import android.content.Context
import com.example.pocemons.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DataBaseModule::class, NetworkModule::class, RepositoryModule::class, ViewFactoryModule::class])
@Singleton
interface AppComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun applicationContext(context: Context): Builder
        fun build(): AppComponent
    }
}