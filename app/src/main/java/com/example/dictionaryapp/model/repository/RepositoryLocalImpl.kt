package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSourceLocal

class RepositoryLocalImpl(
    private val dataSource: DataSourceLocal<List<Word>>
): RepositoryLocal<List<Word>> {
    override suspend fun saveToDB(entity: List<Word>) {
        dataSource.saveToDB(entity)
    }

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}