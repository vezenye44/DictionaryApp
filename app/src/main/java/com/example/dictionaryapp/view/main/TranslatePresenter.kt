package com.example.dictionaryapp.view.main

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.model.datasource.DataSourceLocal
import com.example.dictionaryapp.model.datasource.DataSourceRemote
import com.example.dictionaryapp.model.repository.RepositoryImpl
import com.example.dictionaryapp.presenter.Interactor
import com.example.dictionaryapp.presenter.Presenter
import com.example.dictionaryapp.rx.ISchedulerProvider
import com.example.dictionaryapp.rx.SchedulerProvider
import com.example.dictionaryapp.view.base.View
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

class TranslatePresenter<T : AppState, V : View>(
    private val interactor: Interactor<AppState> = TranslateInteractor(
        RepositoryImpl(DataSourceRemote()),
        RepositoryImpl(DataSourceLocal()),
    ),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: ISchedulerProvider = SchedulerProvider(),
) : Presenter<T, V> {

    private var currentView: V? = null

    private var viewState: AppState? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
        viewState?.let { view.renderData(it) }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getData(name: String, isOnline: Boolean) {
        compositeDisposable.add(
            interactor.getData(name, isOnline)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView?.renderData(AppState.Loading(null)) }
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {
            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
                viewState = appState
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
                viewState = AppState.Error(e)
            }

            override fun onComplete() {
            }
        }
    }
}