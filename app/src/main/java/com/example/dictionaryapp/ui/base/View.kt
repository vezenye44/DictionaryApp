package com.example.dictionaryapp.ui.base

import com.example.dictionaryapp.model.data.AppState

interface View<T : AppState> {

    fun renderData(data: T)
}