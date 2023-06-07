package com.example.dictionaryapp.ui.main

import com.example.model.models.AppState
import com.example.model.models.Word
import com.example.model.interactor.Interactor
import com.example.repository.repository.base.Repository
import com.example.repository.repository.base.RepositoryLocal

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