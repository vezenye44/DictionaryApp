package com.example.dictionaryapp.di

import androidx.room.Room
import com.example.dictionaryapp.model.connection.AndroidNetworkStatus
import com.example.dictionaryapp.model.connection.INetworkStatus
import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.datasource.base.DataSource
import com.example.dictionaryapp.model.datasource.base.DataSourceLocal
import com.example.dictionaryapp.model.datasource.local.DataSourceLocalImpl
import com.example.dictionaryapp.model.datasource.local.RoomDataBaseImplementation
import com.example.dictionaryapp.room.HistoryDatabase
import com.example.dictionaryapp.model.datasource.remote.DataSourceRemote
import com.example.dictionaryapp.model.datasource.remote.RetrofitImplementation
import com.example.dictionaryapp.model.repository.base.Repository
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.model.repository.base.RepositoryLocal
import com.example.dictionaryapp.model.repository.RepositoryLocalImpl
import com.example.dictionaryapp.ui.history.HistoryInteractor
import com.example.dictionaryapp.ui.history.HistoryViewModel
import com.example.dictionaryapp.ui.main.TranslateInteractor
import com.example.dictionaryapp.ui.main.TranslateViewModel
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }

    single<INetworkStatus> { AndroidNetworkStatus() }

    single<Repository<List<Word>>> { RepositoryImpl(get()) }
    single<RepositoryLocal<List<Word>>> { RepositoryLocalImpl(get()) }

    single<DataSource<List<Word>>> { DataSourceRemote(RetrofitImplementation()) }
    single<DataSourceLocal<List<Word>>> { DataSourceLocalImpl(RoomDataBaseImplementation(get())) }
}

val mainScreen = module {

    factory { TranslateInteractor(get(), get()) }
    factory { TranslateViewModel(get()) }
}

val historyScreen = module {

    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}