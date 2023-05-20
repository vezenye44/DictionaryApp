package com.example.dictionaryapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dictionaryapp.di.factores.ViewModelFactory
import com.example.dictionaryapp.di.factores.ViewModelKey
import com.example.dictionaryapp.ui.main.TranslateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [InteractorModule::class])
internal abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TranslateViewModel::class)
    protected abstract fun translateViewModel(translateViewModel: TranslateViewModel): ViewModel
}
