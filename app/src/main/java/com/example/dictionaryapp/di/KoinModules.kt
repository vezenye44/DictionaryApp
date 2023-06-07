package com.example.dictionaryapp.di

import androidx.room.Room
import com.example.model.models.Word
import com.example.datasource.datasource.base.DataSource
import com.example.datasource.datasource.base.DataSourceLocal
import com.example.datasource.datasource.local.DataSourceLocalImpl
import com.example.datasource.datasource.local.RoomDataBaseImplementation
import com.example.datasource.room.HistoryDatabase
import com.example.datasource.datasource.remote.DataSourceRemote
import com.example.datasource.datasource.remote.RetrofitImplementation
import com.example.repository.repository.base.Repository
import com.example.repository.repository.RepositoryImpl
import com.example.repository.repository.base.RepositoryLocal
import com.example.repository.repository.RepositoryLocalImpl
import com.example.historyscreen.HistoryInteractor
import com.example.historyscreen.HistoryViewModel
import com.example.dictionaryapp.ui.main.TranslateInteractor
import com.example.dictionaryapp.ui.main.TranslateViewModel
import com.example.networkstatus.AndroidNetworkStatus
import com.example.networkstatus.INetworkStatus
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }

    single<INetworkStatus> { AndroidNetworkStatus() }

    single<Repository<List<Word>>> { RepositoryImpl(get()) }
    single<RepositoryLocal<List<Word>>> { RepositoryLocalImpl(get()) }

    single<DataSource<List<Word>>> {
        DataSourceRemote(
            RetrofitImplementation()
        )
    }
    single<DataSourceLocal<List<Word>>> {
        DataSourceLocalImpl(
            RoomDataBaseImplementation(get())
        )
    }
}

val mainScreen = module {

    factory { TranslateInteractor(get(), get()) }
    factory { TranslateViewModel(get()) }
}

val historyScreen = module {

    factory { HistoryInteractor(get(), get()) }
    factory { HistoryViewModel(get()) }
}