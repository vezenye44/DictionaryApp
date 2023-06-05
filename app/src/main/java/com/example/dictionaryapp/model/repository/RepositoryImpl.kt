package com.example.dictionaryapp.model.repository

import com.example.dictionaryapp.model.data.Word
import com.example.dictionaryapp.model.datasource.DataSource

class RepositoryImpl(
    private val dataSource: DataSource<List<Word>>,
) : Repository<List<Word>> {

    override suspend fun getData(word: String): List<Word> {
        return dataSource.getData(word)
    }
}
