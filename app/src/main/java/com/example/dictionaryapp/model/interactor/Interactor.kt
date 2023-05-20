package com.example.dictionaryapp.model.interactor

import com.example.dictionaryapp.model.data.AppState
import io.reactivex.rxjava3.core.Observable

interface Interactor<T: AppState> {

    fun getData(name: String, fromRemoteSource: Boolean): Observable<T>
}
