package com.example.dictionaryapp.model.interactor

import com.example.dictionaryapp.model.data.AppState

interface Interactor<T : AppState> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
