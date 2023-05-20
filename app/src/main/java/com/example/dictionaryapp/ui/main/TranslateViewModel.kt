package com.example.dictionaryapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.observers.DisposableObserver
import javax.inject.Inject

class TranslateViewModel @Inject constructor(
    private val interactor: TranslateInteractor,
) : BaseViewModel<AppState>() {

    private var appState: AppState? = null

    override fun getData(word: String, isOnline: Boolean): LiveData<AppState> {
        compositeDisposable.add(
            interactor.getData(word, isOnline).subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe {
                    doOnSubscribe()
                }
                .subscribeWith(getObserver())
        )
        return super.getData(word, isOnline)
    }

    private fun doOnSubscribe() {
        liveDataForViewToObserve.value =
            if (appState != null) appState
            else AppState.Loading(null)
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(state: AppState) {
                appState = state
                liveDataForViewToObserve.value = state
            }

            override fun onError(e: Throwable) {
                liveDataForViewToObserve.value = AppState.Error(e)
            }

            override fun onComplete() {}
        }
    }
}