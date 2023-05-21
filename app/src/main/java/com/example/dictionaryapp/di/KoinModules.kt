package com.example.dictionaryapp.di

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource
import com.example.dictionaryapp.model.datasource.local.DataSourceLocal
import com.example.dictionaryapp.model.datasource.local.RoomDataBaseImplementation
import com.example.dictionaryapp.model.datasource.remote.DataSourceRemote
import com.example.dictionaryapp.model.datasource.remote.RetrofitImplementation
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.ui.main.TranslateInteractor
import com.example.dictionaryapp.ui.main.TranslateViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {

    single<Repository<List<Word>>>(named(NAME_REMOTE)) { RepositoryImpl(get(named(NAME_REMOTE))) }
    single<Repository<List<Word>>>(named(NAME_LOCAL)) { RepositoryImpl(get(named(NAME_LOCAL))) }

    single<DataSource<List<Word>>>(named(NAME_REMOTE)) { DataSourceRemote(RetrofitImplementation()) }
    single<DataSource<List<Word>>>(named(NAME_LOCAL)) { DataSourceLocal(RoomDataBaseImplementation()) }
}

val mainScreen = module {

    factory { TranslateInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { TranslateViewModel(get()) }
}