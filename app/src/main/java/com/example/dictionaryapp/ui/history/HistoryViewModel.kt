package com.example.dictionaryapp.ui.history

import com.example.dictionaryapp.model.models.AppState
import com.example.dictionaryapp.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val interactor: HistoryInteractor,
) : BaseViewModel<AppState>() {

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()
        viewModelScope.launch { startInteractor(word, isOnline) }
    }

    private suspend fun startInteractor(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.postValue(
            interactor.getData(
                word,
                isOnline
            )
        )
    }

    override fun handleError(error: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}