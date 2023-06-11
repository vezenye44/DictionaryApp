package com.example.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel<T>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData<T>(),
) : ViewModel() {

    protected val viewModelScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable -> handleError(throwable) }
    )

    abstract fun handleError(throwable: Throwable)

    abstract fun getData(word: String, isOnline: Boolean)

    open fun subscribe(): LiveData<T> {
        return liveDataForViewToObserve
    }

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelScope.coroutineContext.cancelChildren()
    }
}