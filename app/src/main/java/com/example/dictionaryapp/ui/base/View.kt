package com.example.dictionaryapp.ui.base

import com.example.dictionaryapp.model.models.AppState

interface View<T : AppState> {

    fun renderData(data: T)
}