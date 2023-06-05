package com.example.dictionaryapp.ui.main

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.interactor.Interactor
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryLocal

class TranslateInteractor(
    private val remoteRepository: Repository<List<Word>>,
    private val localRepository: RepositoryLocal<List<Word>>,

    ) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            val data = remoteRepository.getData(word)
            localRepository.saveToDB(data)
            AppState.Success(data)
        } else {
            AppState.Success(localRepository.getData(word))
        }
    }
}