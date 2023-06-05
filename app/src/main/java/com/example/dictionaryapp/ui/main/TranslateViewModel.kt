package com.example.dictionaryapp.ui.main

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TranslateViewModel(
    private val interactor: TranslateInteractor,
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null
    override fun handleError(throwable: Throwable) {
        liveDataForViewToObserve.value = AppState.Error(throwable)
    }

    override fun getData(word: String, isOnline: Boolean) {
        liveDataForViewToObserve.value = AppState.Loading(null)
        cancelJob()

        viewModelScope.launch {
            loadData(word, isOnline)
        }
    }

    private suspend fun loadData(word: String, isOnline: Boolean) {
        withContext(Dispatchers.IO) {
            liveDataForViewToObserve.postValue(interactor.getData(word, isOnline))
        }
    }

    override fun onCleared() {
        liveDataForViewToObserve.value = AppState.Success(null)
        super.onCleared()
    }
}