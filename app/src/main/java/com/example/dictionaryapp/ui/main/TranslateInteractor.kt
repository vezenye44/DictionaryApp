package com.example.dictionaryapp.ui.main

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.interactor.Interactor
import com.example.dictionaryapp.model.repository.Repository

class TranslateInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: Repository<List<Word>>,

    ) : Interactor<AppState> {

    override suspend fun getData(name: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            AppState.Success(remoteRepository.getData(name))
        } else {
            AppState.Success(localRepository.getData(name))
        }
    }
}