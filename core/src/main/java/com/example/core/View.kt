package com.example.core

import com.example.model.models.AppState

interface View<T : AppState> {

    fun renderData(data: T)
}