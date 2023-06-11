package com.example.historyscreen

import com.example.core.BaseViewModel
import com.example.model.models.AppState
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

    override fun handleError(throwable: Throwable) {
        liveDataForViewToObserve.postValue(AppState.Error(throwable))
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}