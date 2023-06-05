package com.example.dictionaryapp.ui.history

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.interactor.Interactor
import com.example.dictionaryapp.model.repository.Repository
import com.example.dictionaryapp.model.repository.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState{
        return if (fromRemoteSource) {
                AppState.Success(repositoryRemote.getData(word))
            } else {
                AppState.Success(repositoryLocal.getData(word))
            }
    }
}