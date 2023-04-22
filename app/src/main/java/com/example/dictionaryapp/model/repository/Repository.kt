package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.AppState
import io.reactivex.rxjava3.core.Observable

interface Repository<T: AppState> {

    fun getData(word: String): Observable<T>
}