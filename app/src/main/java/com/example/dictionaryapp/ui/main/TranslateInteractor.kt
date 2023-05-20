package com.example.dictionaryapp.ui.main

import com.example.dictionaryapp.di.NAME_LOCAL
import com.example.dictionaryapp.di.NAME_REMOTE
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.interactor.Interactor
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

class TranslateInteractor @Inject constructor(
    @Named(NAME_REMOTE) val remoteRepository: Repository<List<Word>>,
    @Named(NAME_LOCAL) val localRepository: Repository<List<Word>>

) : Interactor<AppState> {

    override fun getData(name: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(name).map { AppState.Success(it) }
        } else {
            localRepository.getData(name).map { AppState.Success(it) }
        }
    }
}