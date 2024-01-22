package com.example.model.interactor

import com.example.model.models.AppState

interface Interactor<T : AppState> {

    suspend fun getData(word: String, fromRemoteSource: Boolean): T
}
