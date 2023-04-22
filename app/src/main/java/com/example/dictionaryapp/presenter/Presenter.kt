package com.example.dictionaryapp.presenter

import com.example.dictionaryapp.model.data.AppState
import com.example.dictionaryapp.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)

    fun getData(name: String, isOnline: Boolean)
}