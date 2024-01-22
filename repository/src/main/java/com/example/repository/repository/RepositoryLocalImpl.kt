package com.example.repository.repository

import com.example.datasource.datasource.base.DataSourceLocal
import com.example.model.models.Word
import com.example.repository.repository.base.RepositoryLocal

class RepositoryLocalImpl(
    private val dataSource: DataSourceLocal<List<Word>>,
) : RepositoryLocal<List<Word>> {
    override suspend fun saveToDB(entity: List<Word>) {
        dataSource.saveToDB(entity)
    }

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}