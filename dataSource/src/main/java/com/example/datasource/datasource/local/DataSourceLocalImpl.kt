package com.example.datasource.datasource.local

import com.example.datasource.datasource.base.DataSourceLocal
import com.example.model.models.Word

class DataSourceLocalImpl(
    private val remoteProvider: RoomDataBaseImplementation,
) : DataSourceLocal<List<Word>> {
    override suspend fun saveToDB(entity: List<Word>) {
        remoteProvider.saveToDB(entity)
    }

    override suspend fun getData(word: String): List<Word> =
        remoteProvider.getData(word)
}