package com.example.dictionaryapp.ui.history

import com.example.dictionaryapp.model.models.AppState
import com.example.dictionaryapp.model.models.Word
import com.example.dictionaryapp.model.interactor.Interactor
import com.example.dictionaryapp.model.repository.base.Repository
import com.example.dictionaryapp.model.repository.base.RepositoryLocal

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