package com.example.dictionaryapp.model.datasource

interface DataSourceLocal<T>: DataSource<T> {

    suspend fun saveToDB(entity: T)
}