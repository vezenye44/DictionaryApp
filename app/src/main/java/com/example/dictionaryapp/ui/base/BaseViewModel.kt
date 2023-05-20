package com.example.dictionaryapp.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.rx.ISchedulerProvider
import com.example.dictionaryapp.rx.SchedulerProvider
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T>(
    protected val liveDataForViewToObserve: MutableLiveData<T> = MutableLiveData<T>(),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: ISchedulerProvider = SchedulerProvider(),
) : ViewModel() {

    open fun getData(word: String, isOnline: Boolean): LiveData<T> = liveDataForViewToObserve

    open fun subscribe(): LiveData<T> {
        return liveDataForViewToObserve
    }

    override fun onCleared() = compositeDisposable.clear()
}