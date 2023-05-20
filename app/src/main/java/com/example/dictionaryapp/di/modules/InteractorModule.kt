package com.example.dictionaryapp.di.modules

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.ui.main.TranslateInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module( includes = [RepositoryModule::class] )
class InteractorModule {
    @Provides
    @Singleton
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<Word>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<Word>>
    ) = TranslateInteractor(repositoryRemote, repositoryLocal)
}