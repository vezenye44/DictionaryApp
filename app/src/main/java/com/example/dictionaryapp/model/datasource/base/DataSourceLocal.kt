package com.example.dictionaryapp.model.datasource.base

interface DataSourceLocal<T>: DataSource<T> {

    suspend fun saveToDB(entity: T)
}