package com.example.dictionaryapp.view.base

import com.example.dictionaryapp.model.data.AppState

interface View {

    fun renderData(data: AppState)
}