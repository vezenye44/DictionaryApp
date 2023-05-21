package com.example.dictionaryapp.ui.main

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.interactor.Interactor
import com.example.dictionaryapp.model.repository.Repository
import io.reactivex.rxjava3.core.Observable

class TranslateInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: Repository<List<Word>>,

    ) : Interactor<AppState> {

    override fun getData(name: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(name).map { AppState.Success(it) }
        } else {
            localRepository.getData(name).map { AppState.Success(it) }
        }
    }
}