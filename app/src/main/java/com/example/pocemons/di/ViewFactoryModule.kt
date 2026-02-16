package com.example.pocemons.di

import android.widget.ViewSwitcher
import com.example.pocemons.data.repository.PokeRepository
import com.example.pocemons.ui.viewmodels.PokeViewmodelFactory
import com.example.pocemons.utils.Prefs
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewFactoryModule {

    @Singleton
    @Provides
    fun provideViewFactory(repository: PokeRepository, prefs: Prefs): PokeViewmodelFactory {
        return PokeViewmodelFactory(repository, prefs)
    }
}