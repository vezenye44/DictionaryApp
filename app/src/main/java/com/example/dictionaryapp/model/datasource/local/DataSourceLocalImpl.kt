package com.example.dictionaryapp.model.datasource.local

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSourceLocal

class DataSourceLocalImpl(
    private val remoteProvider: RoomDataBaseImplementation,
) : DataSourceLocal<List<Word>> {
    override suspend fun saveToDB(entity: List<Word>) {
        remoteProvider.saveToDB(entity)
    }

    override suspend fun getData(word: String): List<Word> =
        remoteProvider.getData(word)
}