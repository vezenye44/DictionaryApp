package com.example.dictionaryapp.di.modules

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource
import com.example.dictionaryapp.model.datasource.local.RoomDataBaseImplementation
import com.example.dictionaryapp.model.datasource.remote.RetrofitImplementation
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<Word>>,
    ): Repository<List<Word>> {
        return RepositoryImpl(dataSourceRemote)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<Word>>,
    ): Repository<List<Word>> {
        return RepositoryImpl(dataSourceLocal)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<Word>> {
        return RetrofitImplementation()
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<Word>> {
        return RoomDataBaseImplementation()
    }
}
