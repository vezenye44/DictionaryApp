package com.example.dictionaryapp.model.datasource

import com.example.dictionaryapp.model.data.AppState
import io.reactivex.rxjava3.core.Observable

interface DataSource<T: AppState> {

    fun getData(word: String): Observable<T>
}