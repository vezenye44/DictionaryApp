package com.example.datasource.datasource.base

interface DataSourceLocal<T>: DataSource<T> {

    suspend fun saveToDB(entity: T)
}