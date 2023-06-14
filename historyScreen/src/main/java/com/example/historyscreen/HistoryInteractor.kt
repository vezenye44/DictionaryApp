package com.example.historyscreen

import com.example.model.interactor.Interactor
import com.example.model.models.AppState
import com.example.model.models.Word
import com.example.repository.repository.base.Repository
import com.example.repository.repository.base.RepositoryLocal

class HistoryInteractor(
    private val repositoryRemote: Repository<List<Word>>,
    private val repositoryLocal: RepositoryLocal<List<Word>>,
) : Interactor<AppState> {

    override suspend fun getData(word: String, fromRemoteSource: Boolean): AppState {
        return if (fromRemoteSource) {
            AppState.Success(repositoryRemote.getData(word))
        } else {
            AppState.Success(repositoryLocal.getData(word))
        }
    }
}